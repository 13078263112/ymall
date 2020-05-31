package com.ywc.ymall.sms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.sms.entity.HomeNewProduct;
import com.ywc.ymall.sms.mapper.HomeNewProductMapper;
import com.ywc.ymall.sms.service.HomeNewProductService;
import com.ywc.ymall.vo.PageInfoVo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 新鲜好物表 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
@Component
public class HomeNewProductServiceImpl extends ServiceImpl<HomeNewProductMapper, HomeNewProduct> implements HomeNewProductService {

    @Override
    public PageInfoVo listNewProductForPage(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {

        QueryWrapper<HomeNewProduct> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(productName)){
            wrapper.like("product_name",productName);
        }
        if(recommendStatus!=null){
            wrapper.eq("recommend_status",recommendStatus);
        }
        IPage<HomeNewProduct> iPage = this.baseMapper.selectPage(new Page<HomeNewProduct>(pageNum, pageSize), wrapper);
        return PageInfoVo.getVo(iPage,pageSize.longValue());
    }

    @Override
    public void create(List<HomeNewProduct> homeBrandList) {
        homeBrandList.forEach(homeNewProduct -> {
            homeNewProduct.setRecommendStatus(0);
            homeNewProduct.setSort(0);
            this.baseMapper.insert(homeNewProduct);
        });
    }

    @Override
    public void updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        ids.forEach((id)->{
            HomeNewProduct product = new HomeNewProduct();
            product.setId(id);
            product.setRecommendStatus(recommendStatus);
            this.baseMapper.updateById(product);
        });
    }

    @Override
    public void updateSort(Long id, Integer sort) {
        HomeNewProduct product = new HomeNewProduct();
        product.setId(id);
        product.setSort(sort);
        this.baseMapper.updateById(product);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        ids.forEach(id->{
            this.baseMapper.deleteById(id);
        });
    }
}
