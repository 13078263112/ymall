package com.ywc.ymall.controller.pms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.ymall.pms.service.BrandService;
import com.ywc.ymall.to.ResultParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 品牌功能
 * @author 嘟嘟~
 * @date 2020/4/14 17:33
 */
@CrossOrigin
@RestController
@Api(tags = "PmsBrandController",description = "商品品牌管理")
@RequestMapping("/brand")
public class PmsBrandController {
    @Reference
    BrandService brandService;
    @ApiOperation(value = "根据品牌名称分页获取品牌列表")
    @GetMapping(value = "/list")
    public Object getList(@RequestParam(value = "keyword", required = false) String keyword,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {

        // 根据品牌名称分页获取品牌列表
        Map<String,Object> brandPageInfo =  brandService.pageBrand(keyword,pageNum,pageSize);

        return new ResultParam().success(brandPageInfo);
    }

}
