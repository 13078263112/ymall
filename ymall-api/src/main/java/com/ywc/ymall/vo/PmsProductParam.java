package com.ywc.ymall.vo;

import com.ywc.ymall.cms.entity.PrefrenceAreaProductRelation;
import com.ywc.ymall.cms.entity.SubjectProductRelation;
import com.ywc.ymall.pms.entity.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author 嘟嘟~
 * @date 2020/4/16 14:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PmsProductParam extends Product implements Serializable {
    @ApiModelProperty("商品阶梯价格设置")
    private List<ProductLadder> productLadderList;
    @ApiModelProperty("商品满减价格设置")
    private List<ProductFullReduction> productFullReductionList;
    @ApiModelProperty("商品会员价格设置")
    private List<MemberPrice> memberPriceList;
    @ApiModelProperty("商品的sku库存信息")
    private List<SkuStock> skuStockList;
    @ApiModelProperty("商品参数及自定义规格属性")
    private List<ProductAttributeValue> productAttributeValueList;
    @ApiModelProperty("专题和商品关系")
    private List<SubjectProductRelation> subjectProductRelationList;
    @ApiModelProperty("优选专区和商品的关系")
    private List<PrefrenceAreaProductRelation> prefrenceAreaProductRelationList;
}

