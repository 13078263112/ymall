package com.ywc.ymall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.pms.entity.Product;
import com.ywc.ymall.pms.mapper.ProductMapper;
import com.ywc.ymall.pms.service.ProductService;
import com.ywc.ymall.vo.PageInfoVo;
import com.ywc.ymall.vo.PmsProductQueryParam;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
@Component
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Override
    public PageInfoVo pageProduct(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum) {
        Page<Product> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
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
}
