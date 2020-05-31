package com.ywc.ymall.controller.sms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.ymall.sms.entity.HomeNewProduct;
import com.ywc.ymall.sms.service.HomeNewProductService;
import com.ywc.ymall.to.ResultParam;
import com.ywc.ymall.vo.PageInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 嘟嘟~
 * @date 2020/6/1 0:48
 */
@CrossOrigin
@RestController
@Api(tags = "SmsHomeNewProductController", description = "首页新品管理")
@RequestMapping("/home/newProduct")
public class SmsHomeNewProductController {
    @Reference
    HomeNewProductService homeNewProductService;
    @ApiOperation("分页查询推荐")
    @GetMapping(value = "/list")
    public Object list(@RequestParam(value = "productName", required = false) String productName,
                             @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageInfoVo homeBrandList = homeNewProductService.listNewProductForPage(productName, recommendStatus, pageSize, pageNum);
        return new ResultParam().success(homeBrandList);
    }
    @ApiOperation("添加首页推荐品牌")
    @PostMapping(value = "/create")
    public Object create(@RequestBody List<HomeNewProduct> homeBrandList) {
        homeNewProductService.create(homeBrandList);
        return new ResultParam().success(null);
    }
    @ApiOperation("批量修改推荐状态")
    @PostMapping(value = "/update/recommendStatus")
    public Object updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        homeNewProductService.updateRecommendStatus(ids, recommendStatus);
        return new ResultParam().success(null);
    }
    @ApiOperation("修改推荐排序")
    @PostMapping(value = "/update/sort/{id}")
    public Object updateSort(@PathVariable Long id, Integer sort) {
        homeNewProductService.updateSort(id, sort);
        return new ResultParam().success(null);
    }
    @ApiOperation("批量删除推荐")
    @PostMapping(value = "/delete")
    public Object delete(@RequestParam("ids") List<Long> ids) {
        homeNewProductService.deleteByIds(ids);
        return new ResultParam().success(null);
    }
}
