package com.ywc.ymall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.constant.RedisCacheConstant;
import com.ywc.ymall.pms.entity.ProductCategory;
import com.ywc.ymall.pms.mapper.ProductCategoryMapper;
import com.ywc.ymall.pms.service.ProductCategoryService;
import com.ywc.ymall.to.PmsProductCategoryWithChildrenItem;
import jdk.nashorn.internal.lookup.MethodHandleFactory;
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
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Override
    public List<PmsProductCategoryWithChildrenItem> listWithChildren() {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();

        String cache = ops.get(RedisCacheConstant.PRODUCT_CATEGORY_CACHE_KEY);

        if(!StringUtils.isEmpty(cache)){
            //转化过来返回出去
            List<PmsProductCategoryWithChildrenItem> items = JSON.parseArray(cache, PmsProductCategoryWithChildrenItem.class);
            return items;
        }

        ProductCategoryMapper baseMapper = getBaseMapper();
        List<PmsProductCategoryWithChildrenItem> items = baseMapper.listWithChildren(0);
        //存数据都给一个过期时间比较好；
        String jsonString = JSON.toJSONString(items);
        ops.set(RedisCacheConstant.PRODUCT_CATEGORY_CACHE_KEY,jsonString,3, TimeUnit.DAYS);
        //查某个菜单的所有子菜单
        //TODO 这个数据加缓存，
        return items;
    }
}
