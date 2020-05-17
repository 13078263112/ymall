package com.ywc.ymall.controller.pms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.ymall.pms.service.ProductAttributeCategoryService;
import com.ywc.ymall.to.ResultParam;
import com.ywc.ymall.vo.PageInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**商品属性分类管理
 * @author 嘟嘟~
 * @date 2020/4/16 13:34
 */
@CrossOrigin
@RestController
@Api(tags = "PmsProductAttributeCategoryController", description = "商品属性分类管理")
@RequestMapping("/productAttribute/category")
public class PmsProductAttributeCategoryController {
    @Reference
    ProductAttributeCategoryService productAttributeCategoryService;

    @ApiOperation("分页获取所有商品属性分类")
    @GetMapping(value = "/list")
    public Object getList(@RequestParam(defaultValue = "5") Integer pageSize,
                          @RequestParam(defaultValue = "1") Integer pageNum) {
        return new ResultParam().success(productAttributeCategoryService.roductAttributeCategoryPageInfo(pageNum,pageSize));
    }
    @ApiOperation("获取所有商品属性分类及其下属性【难度较高】")
    @RequestMapping(value = "/list/withAttr", method = RequestMethod.GET)
    @ResponseBody
    public Object getListWithAttr() {

        //TODO 获取所有商品属性分类及其下属性
        return new ResultParam().success(null);
    }

}
