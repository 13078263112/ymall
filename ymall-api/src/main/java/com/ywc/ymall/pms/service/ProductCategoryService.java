package com.ywc.ymall.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ywc.ymall.pms.entity.ProductCategory;
import com.ywc.ymall.pms.entity.ProductCategoryAttributeRelation;
import com.ywc.ymall.to.PmsProductCategoryWithChildrenItem;
import com.ywc.ymall.vo.PageInfoVo;

import java.util.List;

/**
 * @author 嘟嘟~
 * @date 2020/5/8 13:30
 */
public interface ProductCategoryService extends IService<ProductCategory> {

    List<PmsProductCategoryWithChildrenItem> listWithChildren(Integer id);
    PageInfoVo categoryCategoryPageInfo(Long parentId, Integer pageNum, Integer pageSize);
    ProductCategory queryById(Long id);

    List<ProductCategory> queryByParentId(Long parentId);

    List<PmsProductCategoryWithChildrenItem> listWithChildrenById(Integer parentId);
}
