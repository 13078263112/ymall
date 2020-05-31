package com.ywc.ymall.pms.mapper;

import com.ywc.ymall.pms.entity.ProductAttributeCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ywc.ymall.to.PmsProductAttributeCategoryWithChildrenItem;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 Mapper 接口
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
public interface ProductAttributeCategoryMapper extends BaseMapper<ProductAttributeCategory> {
    List<PmsProductAttributeCategoryWithChildrenItem> getListWithAttr();
}
