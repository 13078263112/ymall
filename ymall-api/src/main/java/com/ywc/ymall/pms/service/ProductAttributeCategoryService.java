package com.ywc.ymall.pms.service;

import com.ywc.ymall.pms.entity.ProductAttributeCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ywc.ymall.to.PmsProductAttributeCategoryWithChildrenItem;
import com.ywc.ymall.vo.PageInfoVo;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
public interface ProductAttributeCategoryService extends IService<ProductAttributeCategory> {

    PageInfoVo roductAttributeCategoryPageInfo(Integer pageNum, Integer pageSize);

    void updateProductAttributeCategoryById(Long id, String name);

    void deleteProductAttributeCategoryById(Long id);

    ProductAttributeCategory queryById(Long id);

    void createProductAttributeCategory(String name);

    List<PmsProductAttributeCategoryWithChildrenItem> getListWithAttr();
}
