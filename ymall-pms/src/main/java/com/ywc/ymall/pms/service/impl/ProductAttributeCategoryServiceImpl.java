package com.ywc.ymall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.pms.entity.Product;
import com.ywc.ymall.pms.entity.ProductAttributeCategory;
import com.ywc.ymall.pms.mapper.ProductAttributeCategoryMapper;
import com.ywc.ymall.pms.service.ProductAttributeCategoryService;
import com.ywc.ymall.vo.PageInfoVo;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 产品属性分类表 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
@Component
public class ProductAttributeCategoryServiceImpl extends ServiceImpl<ProductAttributeCategoryMapper, ProductAttributeCategory> implements ProductAttributeCategoryService {

    @Override
    public PageInfoVo roductAttributeCategoryPageInfo(Integer pageNum, Integer pageSize) {
        Page<ProductAttributeCategory> page = new Page<>(pageNum, pageSize);
        QueryWrapper<ProductAttributeCategory> wrapper = new QueryWrapper<>();
        IPage<ProductAttributeCategory> productAttributeCategoryIPage = this.baseMapper.selectPage(page, wrapper);
        PageInfoVo pageInfoVo = new PageInfoVo(productAttributeCategoryIPage.getTotal(),productAttributeCategoryIPage.getPages(),productAttributeCategoryIPage.getSize(),
                productAttributeCategoryIPage.getRecords(),productAttributeCategoryIPage.getCurrent());
        return pageInfoVo;
    }
}
