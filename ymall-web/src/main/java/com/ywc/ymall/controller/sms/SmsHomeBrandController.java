package com.ywc.ymall.controller.sms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.ymall.sms.entity.HomeBrand;
import com.ywc.ymall.sms.service.HomeBrandService;
import com.ywc.ymall.to.ResultParam;
import com.ywc.ymall.vo.PageInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 嘟嘟~
 * @date 2020/6/1 0:35
 */
@CrossOrigin
@RestController
@Api(tags = "SmsHomeBrandController", description = "首页品牌管理")
@RequestMapping("/home/brand")
public class SmsHomeBrandController {
    @Reference
    HomeBrandService homeBrandService;
    @ApiOperation("分页查询推荐品牌")
    @GetMapping(value = "/list")
    public Object list(@RequestParam(value = "brandName", required = false) String brandName,
                             @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageInfoVo homeBrandList = homeBrandService.listBrandForPage(brandName, recommendStatus, pageSize, pageNum);
        return new ResultParam().success(homeBrandList);
    }
    @ApiOperation("批量修改推荐状态")
    @PostMapping(value = "/update/recommendStatus")
    public Object updateRecommendStatus(@RequestParam("ids") List<Long> ids,
                                              @RequestParam Integer recommendStatus) {
        homeBrandService.updateRecommendStatus(ids, recommendStatus);
        return new ResultParam().success(null);
    }
    @ApiOperation("修改品牌排序")
    @PostMapping(value = "/update/sort/{id}")
    public Object updateSort(@PathVariable Long id, Integer sort) {
       homeBrandService.updateSort(id, sort);
        return new ResultParam().success(null);
    }
    @ApiOperation("批量删除推荐品牌")
    @PostMapping(value = "/delete")
    public Object delete(@RequestParam("ids") List<Long> ids) {
        homeBrandService.deleteByIds(ids);
        return new ResultParam().success(null);
    }
    @ApiOperation("添加首页推荐品牌")
    @PostMapping(value = "/create")
    public Object create(@RequestBody List<HomeBrand> homeBrandList) {
        homeBrandService.create(homeBrandList);
        return new ResultParam().success(null);
    }
}
