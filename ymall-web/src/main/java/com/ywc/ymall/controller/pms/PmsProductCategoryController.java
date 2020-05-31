package com.ywc.ymall.controller.pms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.ymall.controller.pms.vo.PmsProductCategoryParam;
import com.ywc.ymall.pms.entity.ProductAttribute;
import com.ywc.ymall.pms.entity.ProductCategory;
import com.ywc.ymall.pms.service.ProductCategoryService;
import com.ywc.ymall.to.ResultParam;
import com.ywc.ymall.to.PmsProductCategoryWithChildrenItem;
import com.ywc.ymall.vo.PageInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品分类管理
 * @author 嘟嘟~
 * @date 2020/4/14 17:53
 */
@CrossOrigin
@RestController
@Api(tags = "PmsProductCategoryController", description = "商品分类管理")
@RequestMapping("/productCategory")
public class PmsProductCategoryController {
    @Reference
    ProductCategoryService productCategoryService;

    @ApiOperation("查询所有一级分类及子分类")
    @GetMapping(value = "/list/withChildren")
    public Object listWithChildren() {
        //查询所有一级分类及子分类
        List<PmsProductCategoryWithChildrenItem> items = productCategoryService.listWithChildren(0);
        return new ResultParam().success(items);
    }

    @ApiOperation("分页查询商品分类")
    @GetMapping(value = "/list/{parentId}")
    public Object getList(@PathVariable Long parentId,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        //TODO 分页查询商品分类
        return new ResultParam().success(productCategoryService.categoryCategoryPageInfo(parentId,pageNum,pageSize));
    }
    @ApiOperation("根据id获取商品分类")
    @GetMapping(value = "/{id}")
    public Object getItem(@PathVariable Long id) {
        return new ResultParam().success(productCategoryService.queryById(id));
    }
    @ApiOperation("添加产品分类")
    @PostMapping(value = "/create")
    public Object create(@Validated @RequestBody PmsProductCategoryParam productCategoryParam,
                         BindingResult result) {
        //TODO 添加产品分类
        ProductCategory productCategory=new ProductCategory();
        BeanUtils.copyProperties(productCategoryParam,productCategory);
        productCategoryService.create(productCategory);
        return new ResultParam().success(true);
    }

    @ApiOperation("删除商品分类")
    @GetMapping(value = "/delete/{id}")
    public Object delete(@PathVariable Long id) {
        //TODO 删除商品分类
        productCategoryService.deleteById(id);
        return new ResultParam().success(null);
    }
    @ApiOperation("修改导航栏显示状态")
    @PostMapping(value = "/update/navStatus")
    public Object updateNavStatus(@RequestParam("ids") List<Long> ids, @RequestParam("navStatus") Integer navStatus) {
        productCategoryService.updateNavStatus(ids,navStatus);
        //TODO 修改导航栏显示状态
        return new ResultParam().success(null);
    }
    @ApiOperation("修改显示状态")
    @PostMapping(value = "/update/showStatus")
    public Object updateShowStatus(@RequestParam("ids") List<Long> ids, @RequestParam("showStatus") Integer showStatus) {
        //TODO 修改显示状态
        productCategoryService.updateShowStatus(ids,showStatus);
        return new ResultParam().success(null);
    }
}
