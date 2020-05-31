package com.ywc.ymall.pms.service;

import com.ywc.ymall.pms.entity.ProductAttribute;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ywc.ymall.vo.PageInfoVo;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
public interface ProductAttributeService extends IService<ProductAttribute> {

    PageInfoVo getCategoryAttributes(Long cid, Integer type, Integer pageSize, Integer pageNum);

    ProductAttribute queryCategoryAttributesById(Long id);

    void deleteCategoryAttributesByIds(List<Long> ids);

    void createProductAttribute(ProductAttribute productAttribute);


    boolean updateProductAttributeById(ProductAttribute productAttribute);


    List<ProductAttribute> queryCategoryAttributesByproductCategoryId(Long productCategoryId);
}
