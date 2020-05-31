package com.ywc.ymall.sms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.pms.entity.ProductCategory;
import com.ywc.ymall.sms.entity.HomeRecommendProduct;
import com.ywc.ymall.sms.mapper.HomeRecommendProductMapper;
import com.ywc.ymall.sms.service.HomeRecommendProductService;
import com.ywc.ymall.vo.PageInfoVo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 人气推荐商品表 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
@Component
public class HomeRecommendProductServiceImpl extends ServiceImpl<HomeRecommendProductMapper, HomeRecommendProduct> implements HomeRecommendProductService {

    @Override
    public List<HomeRecommendProduct> queryAll() {
        QueryWrapper<HomeRecommendProduct> wrapper = new QueryWrapper<>();
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public PageInfoVo listrecommendProductForPage(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        QueryWrapper<HomeRecommendProduct> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(productName)){
            wrapper.like("product_name",productName);
        }
        if(recommendStatus!=null){
            wrapper.eq("recommend_status",recommendStatus);
        }
        IPage<HomeRecommendProduct> iPage = this.baseMapper.selectPage(new Page<HomeRecommendProduct>(pageNum, pageSize), wrapper);
        return PageInfoVo.getVo(iPage,pageSize.longValue());
    }

    @Override
    public void create(List<HomeRecommendProduct> homeBrandList) {
        homeBrandList.forEach(homeRecommendProduct -> {
            homeRecommendProduct.setRecommendStatus(0);
            homeRecommendProduct.setSort(0);
            this.baseMapper.insert(homeRecommendProduct);
        });
    }

    @Override
    public void updateSort(Long id, Integer sort) {
        HomeRecommendProduct product = new HomeRecommendProduct();
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

    @Override
    public void updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        ids.forEach((id)->{
            HomeRecommendProduct product = new HomeRecommendProduct();
            product.setId(id);
            product.setRecommendStatus(recommendStatus);
            this.baseMapper.updateById(product);
        });
    }
}
