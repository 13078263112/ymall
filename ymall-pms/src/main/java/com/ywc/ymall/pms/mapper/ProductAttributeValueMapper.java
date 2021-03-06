package com.ywc.ymall.pms.mapper;

import com.ywc.ymall.pms.entity.ProductAttribute;
import com.ywc.ymall.pms.entity.ProductAttributeValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ywc.ymall.to.es.EsProductAttributeValue;

import java.util.List;

/**
 * <p>
 * 存储产品参数信息的表 Mapper 接口
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
public interface ProductAttributeValueMapper extends BaseMapper<ProductAttributeValue> {
    List<EsProductAttributeValue> selectProductBaseAttrAndValue(Long id);
    List<ProductAttribute> selectProductSaleAttrName(Long id);
}
