package com.ywc.ymall.to;

import com.alibaba.fastjson.JSON;
import com.ywc.ymall.pms.entity.ProductCategory;
import lombok.*;

import java.util.List;

/**
 * @author 嘟嘟~
 * @date 2020/4/14 17:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PmsProductCategoryWithChildrenItem  extends ProductCategory {
    private List<PmsProductCategoryWithChildrenItem> children;


}
