package com.ywc.ymall.pms.service;

import com.ywc.ymall.pms.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ywc.ymall.to.PmsProductCategoryWithChildrenItem;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
public interface ProductCategoryService extends IService<ProductCategory> {

    List<PmsProductCategoryWithChildrenItem> listWithChildren();
}
