package com.ywc.ymall.controller.pms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.ymall.pms.service.ProductCategoryService;
import com.ywc.ymall.to.ResultParam;
import com.ywc.ymall.to.PmsProductCategoryWithChildrenItem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
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
        List<PmsProductCategoryWithChildrenItem> items = productCategoryService.listWithChildren();
        return new ResultParam().success(items);
    }
}
