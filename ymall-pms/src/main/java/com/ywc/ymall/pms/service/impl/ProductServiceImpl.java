package com.ywc.ymall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.constant.EsConstant;
import com.ywc.ymall.pms.entity.*;
import com.ywc.ymall.pms.mapper.*;
import com.ywc.ymall.pms.service.ProductService;
import com.ywc.ymall.to.es.EsProduct;
import com.ywc.ymall.to.es.EsProductAttributeValue;
import com.ywc.ymall.to.es.EsSkuProductInfo;
import com.ywc.ymall.vo.PageInfoVo;
import com.ywc.ymall.vo.PmsProductParam;
import com.ywc.ymall.vo.PmsProductQueryParam;
import io.searchbox.client.JestClient;
import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Slf4j
@Service
@Component
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Autowired
    SkuStockMapper skuStockMapper;
    @Autowired
    ProductLadderMapper productLadderMapper;
    @Autowired
    ProductFullReductionMapper productFullReductionMapper;
    @Autowired
    MemberPriceMapper memberPriceMapper;
    @Autowired
    ProductAttributeValueMapper productAttributeValueMapper;
    @Autowired
    ProductCategoryMapper productCategoryMapper;
    @Autowired
    JestClient jestClient;

    ThreadLocal<Product> productThreadLocal = new ThreadLocal<Product>();
    @Override
    public PageInfoVo pageProduct(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum) {
        Page<Product> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("delete_status",0);
        if(productQueryParam.getBrandId()!=null){
            //前端传了
            wrapper.eq("brand_id",productQueryParam.getBrandId());
        }
        if(!StringUtils.isEmpty(productQueryParam.getKeyword())){
            wrapper.like("name",productQueryParam.getKeyword());
        }
        if(productQueryParam.getProductCategoryId()!=null){
            wrapper.eq("product_category_id",productQueryParam.getProductCategoryId());
        }
        if(!StringUtils.isEmpty(productQueryParam.getProductSn())){
            wrapper.like("product_sn",productQueryParam.getProductSn());
        }
        if(productQueryParam.getPublishStatus()!=null){
            wrapper.eq("publish_status",productQueryParam.getPublishStatus());
        }
        if(productQueryParam.getVerifyStatus()!=null) {
            wrapper.eq("verify_status",productQueryParam.getVerifyStatus());
        }
        //去数据库分页查
        IPage<Product> selectPage = this.baseMapper.selectPage(page, wrapper);

        //封装数据
        PageInfoVo pageInfoVo = new PageInfoVo(selectPage.getTotal(),selectPage.getPages(),selectPage.getSize(),
                selectPage.getRecords(),selectPage.getCurrent());
        return pageInfoVo;
    }

    @Override
    public boolean updateDeleteStatus(List<Long> ids, Integer deleteStatus) {
        int i =0;
        for (Long id : ids) {
            Product product = new Product();
            product.setId(id);
            product.setDeleteStatus(deleteStatus);
           i+=this.baseMapper.updateById(product);
        }
        return i>0?true:false;
    }

    @Override
    public boolean updatePublishStatus(List<Long> ids, Integer publishStatus) {
        //1、上架/下架
        if(publishStatus==1){
            setProductPublishStatus(publishStatus,ids);
            publishProduct(ids);
        }else{
            setProductPublishStatus(publishStatus,ids);
            removeProduct(ids);
        }
        return true;
    }

    private void setProductPublishStatus(Integer publishStatus, List<Long> ids) {
        ids.forEach((id)->{
            Product product = new Product();
            //默认所有属性为null
            product.setId(id);
            product.setPublishStatus(publishStatus);
            this.baseMapper.updateById(product);
        });
    }

    private void removeProduct(List<Long> ids) {
        ids.forEach((id)->{
            Delete delete = new Delete.Builder(id.toString()).index(EsConstant.PRODUCT_ES_INDEX)
                    .type(EsConstant.PRODUCT_INFO_ES_TYPE)
                    .build();
            try {
                DocumentResult execute = jestClient.execute(delete);
                if(execute.isSucceeded()){
                    log.info("商品：{} ==》ES下架完成",id);
                }else {
                    //deleteProductFromEs(id);
                    log.error("商品：{} ==》ES下架失败",id);
                }
            }catch (Exception e){
                //deleteProductFromEs(id);
                log.error("商品：{} ==》ES下架失败",id);
            }
        });
    }

    private void publishProduct(List<Long> ids) {
        //1、查当前需要上架的商品的sku信息和spu信息
        ids.forEach((id)->{
            //1、查出商品的基本新
            Product productInfo = productInfo(id);
            EsProduct esProduct = new EsProduct();
            //1、复制基本信息
            BeanUtils.copyProperties(productInfo,esProduct);
            //2、复制sku信息，对于es要保存商品信息,还要查出这个商品的sku，给es中保存
            List<SkuStock> stocks = skuStockMapper.selectList(new QueryWrapper<SkuStock>().eq("product_id", id));
            List<EsSkuProductInfo> esSkuProductInfos = new ArrayList<>(stocks.size());
            //查出当前商品的sku属性  颜色  尺码
            List<ProductAttribute>  skuAttributeNames = productAttributeValueMapper.selectProductSaleAttrName(id);
            stocks.forEach((skuStock)->{
                EsSkuProductInfo info = new EsSkuProductInfo();
                BeanUtils.copyProperties(skuStock,info);

                //闪亮 黑色
                String subTitle = esProduct.getName();
                if(!StringUtils.isEmpty(skuStock.getSp1())){
                    subTitle+=" "+skuStock.getSp1();
                }
                if(!StringUtils.isEmpty(skuStock.getSp2())){
                    subTitle+=" "+skuStock.getSp2();
                }
                if(!StringUtils.isEmpty(skuStock.getSp3())){
                    subTitle+=" "+skuStock.getSp3();
                }
                //sku的特色标题
                info.setSkuTitle(subTitle);
                List<EsProductAttributeValue> skuAttributeValues = new ArrayList<>();
                for (int i=0;i<skuAttributeNames.size();i++){
                    //skuAttr 颜色/尺码
                    EsProductAttributeValue value = new EsProductAttributeValue();

                    value.setName(skuAttributeNames.get(i).getName());
                    value.setProductId(id);
                    value.setProductAttributeId(skuAttributeNames.get(i).getId());
                    value.setType(skuAttributeNames.get(i).getType());

                    //颜色   尺码;让es去统计‘；改掉查询商品的属性分类里面所有属性的时候，按照sort字段排序好
                    if(i==0){
                        value.setValue(skuStock.getSp1());
                    }
                    if(i==1){
                        value.setValue(skuStock.getSp2());
                    }
                    if(i==2){
                        value.setValue(skuStock.getSp3());
                    }

                    skuAttributeValues.add(value);

                }

                info.setAttributeValues(skuAttributeValues);
                //sku有多个销售属性；颜色，尺码
                esSkuProductInfos.add(info);
                //查出销售属性的名

            });

            esProduct.setSkuProductInfos(esSkuProductInfos);


            List<EsProductAttributeValue> attributeValues = productAttributeValueMapper.selectProductBaseAttrAndValue(id);
            //3、复制公共属性信息，查出这个商品的公共属性
            esProduct.setAttrValueList(attributeValues);

            try {
                //把商品保存到es中
                Index build = new Index.Builder(esProduct)
                        .index(EsConstant.PRODUCT_ES_INDEX)
                        .type(EsConstant.PRODUCT_INFO_ES_TYPE)
                        .id(id.toString())
                        .build();
                DocumentResult execute = jestClient.execute(build);
                boolean succeeded = execute.isSucceeded();
                if(succeeded){
                    log.info("ES中；id为{}商品上架完成",id);
                }else {
                    log.error("ES中；id为{}商品未保存成功，开始重试",id);
                    //saveProductToEs(id);
                }
            }catch (Exception e){
                log.error("ES中；id为{}商品数据保存异常；{}",id,e.getMessage());
                //saveProductToEs(id);
            }
        });

    }

    @Override
    public boolean updateNewStatus(List<Long> ids, Integer newStatus) {
        int i =0;
        for (Long id : ids) {
            Product product = new Product();
            product.setId(id);
            product.setNewStatus(newStatus);
            i+=this.baseMapper.updateById(product);
        }

        return i>0?true:false;
    }

    @Override
    public boolean updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        int i =0;
        for (Long id : ids) {
            Product product = new Product();
            product.setId(id);
            product.setRecommandStatus(recommendStatus);
            i+=this.baseMapper.updateById(product);
        }
        return i>0?true:false;
    }

    @Override
    public Product getUpdateInfo(Long id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public void create(PmsProductParam productParam) {
        ProductServiceImpl psProxy = (ProductServiceImpl) AopContext.currentProxy();
        //保存SPU和SKU【REQUIRES_NEW】
        psProxy.saveBaseProductInfo(productParam);
        //Require
        psProxy.saveProductLadder(productParam.getProductLadderList());//【REQUIRED_NEW】
        psProxy.saveProductFullReduction(productParam.getProductFullReductionList());
        psProxy.saveMemberPrice(productParam.getMemberPriceList());
        psProxy.saveProductAttributeValue(productParam.getProductAttributeValueList());
        psProxy.updateProductCategoryCount();

    }
    @Override
    public void updateProduct(Long id, PmsProductParam productParam) {
        updteProducts(productParam);
        //Require
        if(productParam.getProductLadderList()!=null){
            updateProductLadder(id,productParam.getProductLadderList());//【REQUIRED_NEW】
        }
        if(productParam.getProductFullReductionList()!=null){
            updateProductFullReduction(id,productParam.getProductFullReductionList());
        }
        if(productParam.getMemberPriceList()!=null){
            updateMemberPrice(id,productParam.getMemberPriceList());
        }
        if(productParam.getProductAttributeValueList()!=null){
            updateProductAttributeValue(id,productParam.getProductAttributeValueList());
        }
    }
    @Override
    public Product productInfo(Long id) {
        return this.baseMapper.selectById(id);
    }
    @Override
    public List<Product> queryAllByRecommand() {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("recommand_status",1);
        return this.baseMapper.selectList(wrapper);
    }

    public void updteProducts(PmsProductParam productParam) {
        Product product = new Product();
        BeanUtils.copyProperties(productParam,product);
        baseMapper.updateById(product);
    }
    public void updateProductLadder(Long id,List<ProductLadder> list){
        for (ProductLadder ladder : list) {
            QueryWrapper<ProductLadder> wrapper = new QueryWrapper<ProductLadder>().eq("product_id",id);
            productLadderMapper.update(ladder,wrapper);

        }
    }
    public void updateProductFullReduction(Long id,List<ProductFullReduction> list){
        for (ProductFullReduction reduction : list) {
            QueryWrapper<ProductFullReduction> wrapper = new QueryWrapper<ProductFullReduction>().eq("product_id",id);
            productFullReductionMapper.update(reduction,wrapper);
        }
    }
    public void updateMemberPrice(Long id,List<MemberPrice> memberPrices){

        for (MemberPrice memberPrice : memberPrices) {
            QueryWrapper<MemberPrice> wrapper = new QueryWrapper<MemberPrice>().eq("product_id",id);
            memberPriceMapper.update(memberPrice,wrapper);
        }

    }

    //6、保存参数及自定义规格 到 pms_product_attribute_value（）【REQUIRES_NEW】
    public  void updateProductAttributeValue(Long id,List<ProductAttributeValue> productAttributeValues){

        productAttributeValues.forEach((pav)->{
            QueryWrapper<ProductAttributeValue> wrapper = new QueryWrapper<ProductAttributeValue>().eq("product_id",id);
            productAttributeValueMapper.update(pav,wrapper);
        });
    }



    public void saveBaseProductInfo(PmsProductParam productParam){
        ProductServiceImpl psProxy = (ProductServiceImpl) AopContext.currentProxy();
        //Required
        psProxy.saveProduct(productParam);//【REQUIRES_NEW】
        //Required
        psProxy.saveSkuInfo(productParam.getSkuStockList());
    }
    //1、保存商品的基本信息 pms_product（将刚才保存的这个商品的自增id获取出来）【REQUIRED】

    public Long saveProduct(PmsProductParam productParam) {
        Product product = new Product();
        BeanUtils.copyProperties(productParam,product);
        int insert = baseMapper.insert(product);
        //商品信息共享到ThreadLocal
        productThreadLocal.set(product);
        //map.put(Thread.currentThread(),product);
        return  product.getId();
    }
    //2、保存商品阶梯价格 到 saveProductLadder【REQUIRES_NEW】

    public void saveProductLadder(List<ProductLadder> list){
        Product product = productThreadLocal.get();
        for (ProductLadder ladder : list) {
            ladder.setProductId(product.getId());
            productLadderMapper.insert(ladder);

        }
    }
    //3、保存商品满减价格 到 pms_product_full_reduction【REQUIRES_NEW】

    public void saveProductFullReduction(List<ProductFullReduction> list){
        Product product = productThreadLocal.get();
        for (ProductFullReduction reduction : list) {
            reduction.setProductId(product.getId());
            productFullReductionMapper.insert(reduction);
        }
    }
    //4、保存商品的会员价格 到 pms_member_price【REQUIRES_NEW】{// int i=10/0}

    public void saveMemberPrice(List<MemberPrice> memberPrices){
        Product product = productThreadLocal.get();
        for (MemberPrice memberPrice : memberPrices) {
            memberPrice.setProductId(product.getId());
            memberPriceMapper.insert(memberPrice);
        }
        //lambda
    }
    //5、保存Sku信息

    public void saveSkuInfo(List<SkuStock> skuStocks){
        Product product = productThreadLocal.get();
        for (int i = 1; i<=skuStocks.size(); i++) {
            SkuStock skuStock = skuStocks.get(i-1);
            if(StringUtils.isEmpty(skuStock.getSkuCode())){
                skuStock.setSkuCode(product.getId()+"_"+i);
            }
            skuStock.setProductId(product.getId());
            skuStockMapper.insert(skuStock);
        }
    }
    //6、保存参数及自定义规格 到 pms_product_attribute_value（）【REQUIRES_NEW】
    public  void saveProductAttributeValue(List<ProductAttributeValue> productAttributeValues){
        Product product = productThreadLocal.get();
        productAttributeValues.forEach((pav)->{
            pav.setProductId(product.getId());
            productAttributeValueMapper.insert(pav);
        });
    }
    //7、更新商品分类数目 【REQUIRES_NEW】
    public void updateProductCategoryCount(){
        Product product = productThreadLocal.get();
        Long id = product.getProductCategoryId();
        productCategoryMapper.updateCountById(id);
    }
}
