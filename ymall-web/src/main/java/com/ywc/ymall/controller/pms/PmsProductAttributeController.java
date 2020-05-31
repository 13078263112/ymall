package com.ywc.ymall.controller.pms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.ymall.controller.pms.vo.PmsProductAttributeParam;
import com.ywc.ymall.pms.entity.ProductAttribute;
import com.ywc.ymall.pms.entity.ProductAttributeValue;
import com.ywc.ymall.pms.service.ProductAttributeService;
import com.ywc.ymall.pms.service.ProductAttributeValueService;
import com.ywc.ymall.to.ResultParam;
import com.ywc.ymall.vo.PageInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javafx.print.PrinterAttributes;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品属性管理
 * @author 嘟嘟~
 * @date 2020/4/14 22:48
 */
@CrossOrigin
@RestController
@Api(tags = "PmsProductAttributeController", description = "商品属性管理")
@RequestMapping("/productAttribute")
public class PmsProductAttributeController {
    @Reference
    ProductAttributeService productAttributeService;

    @ApiOperation("根据分类查询属性列表或参数列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "0表示属性，1表示参数", required = true, paramType = "query", dataType = "integer")})
    @GetMapping(value = "/list/{cid}")
    public Object getList(@PathVariable("cid") Long cid,
                          @RequestParam(value = "type") Integer type,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

        //查出这个属性分类下所有的销售属性和基本参数
        PageInfoVo pageInfoVo = productAttributeService.getCategoryAttributes(cid,type,pageSize,pageNum);
        return new ResultParam().success(pageInfoVo);
    }
    @ApiOperation("根据商品分类的id获取商品属性及属性分类")
    @GetMapping(value = "/attrInfo/{productCategoryId}")
    public Object getAttrInfo(@PathVariable Long productCategoryId){
        //TODO 根据分类查询属性列表或参数列表
        List<ProductAttribute> list=productAttributeService.queryCategoryAttributesByproductCategoryId(productCategoryId);
        return new ResultParam().success(list);
    }
    @ApiOperation("查询单个商品属性")
    @GetMapping(value = "/{id}")
    public Object getItem(@PathVariable Long id){
        //TODO 查询单个商品属性
     ProductAttribute productAttribute=productAttributeService.queryCategoryAttributesById(id);
        return new ResultParam().success(productAttribute);
    }

    @ApiOperation("批量删除商品属性")
    @PostMapping(value = "/delete")
    public Object delete(@RequestParam("ids") List<Long> ids){
        //TODO 批量删除商品属性
        productAttributeService.deleteCategoryAttributesByIds(ids);
        return new ResultParam().success(null);
    }
    @ApiOperation("添加商品属性信息")
    @PostMapping(value = "/create")
    public Object create(@RequestBody PmsProductAttributeParam productAttributeParam, BindingResult bindingResult) {
        //TODO 添加商品属性信息
        ProductAttribute productAttribute = new ProductAttribute();
        BeanUtils.copyProperties(productAttributeParam,productAttribute);
        productAttributeService.createProductAttribute(productAttribute);
        return new ResultParam().success(null);
    }
    @ApiOperation("修改商品属性信息")
    @PostMapping(value = "/update/{id}")
    public Object update(@PathVariable Long id,@RequestBody PmsProductAttributeParam productAttributeParam,BindingResult bindingResult){
        //TODO 修改商品属性信息
        ProductAttribute productAttribute = new ProductAttribute();
        BeanUtils.copyProperties(productAttributeParam,productAttribute);
        productAttribute.setId(id);
         boolean fang=  productAttributeService.updateProductAttributeById(productAttribute);
        return new ResultParam().success(fang);
    }
}
