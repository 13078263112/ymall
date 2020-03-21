package com.ywc.ymall.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.pms.entity.ProductCategoryAttributeRelation;
import com.ywc.ymall.pms.mapper.ProductCategoryAttributeRelationMapper;
import com.ywc.ymall.pms.service.ProductCategoryAttributeRelationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
public class ProductCategoryAttributeRelationServiceImpl extends ServiceImpl<ProductCategoryAttributeRelationMapper, ProductCategoryAttributeRelation> implements ProductCategoryAttributeRelationService {

}
