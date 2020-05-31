package com.ywc.ymall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.constant.RedisCacheConstant;
import com.ywc.ymall.pms.entity.ProductCategory;
import com.ywc.ymall.pms.mapper.ProductCategoryMapper;
import com.ywc.ymall.pms.service.ProductCategoryService;
import com.ywc.ymall.to.PmsProductCategoryWithChildrenItem;
import com.ywc.ymall.vo.PageInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
@Component
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements  ProductCategoryService{

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public List<PmsProductCategoryWithChildrenItem> listWithChildren(Integer id) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();

        String cache = ops.get(RedisCacheConstant.PRODUCT_CATEGORY_CACHE_KEY);

        if(!StringUtils.isEmpty(cache)){
            //转化过来返回出去
            List<PmsProductCategoryWithChildrenItem> items = JSON.parseArray(cache, PmsProductCategoryWithChildrenItem.class);
            return items;
        }
        List<PmsProductCategoryWithChildrenItem> items = this.baseMapper.listWithChildren(0);
        //存数据都给一个过期时间比较好；
        String jsonString = JSON.toJSONString(items);
        ops.set(RedisCacheConstant.PRODUCT_CATEGORY_CACHE_KEY,jsonString,3, TimeUnit.DAYS);
        //查某个菜单的所有子菜单
        //TODO 这个数据加缓存，
        return items;
    }

    @Override
    public PageInfoVo categoryCategoryPageInfo(Long parentId,Integer pageNum, Integer pageSize) {
        Page<ProductCategory> page = new Page<>(pageNum, pageSize);
        QueryWrapper<ProductCategory> wrapper = new QueryWrapper<>();

            wrapper.eq("parent_id",parentId);

        IPage<ProductCategory> productCategoryIPage = this.baseMapper.selectPage(page, wrapper);
        PageInfoVo pageInfoVo = new PageInfoVo(productCategoryIPage.getTotal(),productCategoryIPage.getPages(),productCategoryIPage.getSize(),
                productCategoryIPage.getRecords(),productCategoryIPage.getCurrent());
        return pageInfoVo;
    }

    @Override
    public ProductCategory queryById(Long id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<ProductCategory> queryByParentId(Long parentId) {
        QueryWrapper<ProductCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",parentId);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public List<PmsProductCategoryWithChildrenItem> listWithChildrenById(Integer parentId) {

        ValueOperations<String, String> ops = redisTemplate.opsForValue();

        String cache = ops.get(RedisCacheConstant.PRODUCT_CATEGORY_CACHE_KEY+parentId);
        if(!StringUtils.isEmpty(cache)){
            //转化过来返回出去
            List<PmsProductCategoryWithChildrenItem> items = JSON.parseArray(cache, PmsProductCategoryWithChildrenItem.class);
            return items;
        }

        List<PmsProductCategoryWithChildrenItem> items = this.baseMapper.listWithChildren(parentId);
        //存数据都给一个过期时间比较好；
        String jsonString = JSON.toJSONString(items);
        ops.set(RedisCacheConstant.PRODUCT_CATEGORY_CACHE_KEY+parentId,jsonString,3, TimeUnit.DAYS);
        //查某个菜单的所有子菜单
        //TODO 这个数据加缓存，
        return items;
    }

    @Override
    public void create(ProductCategory productCategory) {
        this.baseMapper.insert(productCategory);
    }

    @Override
    public void deleteById(Long id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public void updateNavStatus(List<Long> ids, Integer navStatus) {
        for (Long id : ids) {
            ProductCategory productCategory = new ProductCategory();
            productCategory.setId(id);
            productCategory.setNavStatus(navStatus);
            this.baseMapper.updateById(productCategory);
        }
    }

    @Override
    public void updateShowStatus(List<Long> ids, Integer showStatus) {
        for (Long id : ids) {
            ProductCategory productCategory = new ProductCategory();
            productCategory.setId(id);
            productCategory.setShowStatus(showStatus);
            this.baseMapper.updateById(productCategory);
        }
    }
}
