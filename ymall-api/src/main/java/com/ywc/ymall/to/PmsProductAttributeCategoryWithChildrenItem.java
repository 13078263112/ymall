package com.ywc.ymall.to;

import com.ywc.ymall.pms.entity.ProductAttribute;
import com.ywc.ymall.pms.entity.ProductAttributeCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 嘟嘟~
 * @date 2020/5/27 16:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PmsProductAttributeCategoryWithChildrenItem extends ProductAttributeCategory {
    private List<ProductAttribute> children;
}
