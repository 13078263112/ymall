package com.ywc.ymall.controller.sms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.ymall.sms.entity.HomeRecommendProduct;
import com.ywc.ymall.sms.service.HomeRecommendProductService;
import com.ywc.ymall.to.ResultParam;
import com.ywc.ymall.vo.PageInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 嘟嘟~
 * @date 2020/6/1 1:04
 */
@CrossOrigin
@RestController
@Api(tags = "SmsHomeRecommendProductController", description = "首页人气推荐管理")
@RequestMapping("/home/recommendProduct")
public class SmsHomeRecommendProductController {
    @Reference
    HomeRecommendProductService homeRecommendProductService;
    @ApiOperation("分页查询推荐")
    @GetMapping(value = "/list")
    public Object list(@RequestParam(value = "productName", required = false) String productName,
                             @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageInfoVo homeBrandList = homeRecommendProductService.listrecommendProductForPage(productName, recommendStatus, pageSize, pageNum);
        return new ResultParam().success(homeBrandList);
    }
    @ApiOperation("添加首页推荐")
    @PostMapping(value = "/create")

    public Object create(@RequestBody List<HomeRecommendProduct> homeBrandList) {
        homeRecommendProductService.create(homeBrandList);
        return new ResultParam().success(null);
    }

    @ApiOperation("修改推荐排序")
    @PostMapping(value = "/update/sort/{id}")
    public Object updateSort(@PathVariable Long id, Integer sort) {
        homeRecommendProductService.updateSort(id, sort);
        return new ResultParam().success(null);
    }

    @ApiOperation("批量删除推荐")
    @PostMapping(value = "/delete")
    public Object delete(@RequestParam("ids") List<Long> ids) {
        homeRecommendProductService.deleteByIds(ids);
        return new ResultParam().success(null);
    }

    @ApiOperation("批量修改推荐状态")
    @PostMapping(value = "/update/recommendStatus")
    public Object updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        homeRecommendProductService.updateRecommendStatus(ids, recommendStatus);
        return new ResultParam().success(null);
    }
}
