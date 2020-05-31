package com.ywc.ymall.controller.sms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.ymall.sms.entity.HomeRecommendSubject;
import com.ywc.ymall.sms.service.HomeRecommendSubjectService;
import com.ywc.ymall.to.ResultParam;
import com.ywc.ymall.vo.PageInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 嘟嘟~
 * @date 2020/6/1 1:15
 */
@CrossOrigin
@RestController
@Api(tags = "SmsHomeRecommendSubjectController", description = "首页专题推荐管理")
@RequestMapping("/home/recommendSubject")
public class SmsHomeRecommendSubjectController {
    @Reference
    HomeRecommendSubjectService homeRecommendSubjectService;
    @ApiOperation("添加首页推荐专题")
    @PostMapping(value = "/create")
    public Object create(@RequestBody List<HomeRecommendSubject> homeBrandList) {
        homeRecommendSubjectService.create(homeBrandList);
        return new ResultParam().success(null);
    }

    @ApiOperation("修改推荐排序")
    @PostMapping(value = "/update/sort/{id}")
    public Object updateSort(@PathVariable Long id, Integer sort) {
        homeRecommendSubjectService.updateSort(id, sort);
        return new ResultParam().success(null);
    }

    @ApiOperation("批量删除推荐")
    @PostMapping(value = "/delete")
    public Object delete(@RequestParam("ids") List<Long> ids) {
        homeRecommendSubjectService.deleteByIds(ids);
        return new ResultParam().success(null);
    }

    @ApiOperation("批量修改推荐状态")
    @PostMapping(value = "/update/recommendStatus")
    public Object updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        homeRecommendSubjectService.updateRecommendStatus(ids, recommendStatus);
        return new ResultParam().success(null);
    }

    @ApiOperation("分页查询推荐")
    @GetMapping(value = "/list")
    public Object list(@RequestParam(value = "subjectName", required = false) String subjectName,
                             @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageInfoVo homeBrandList = homeRecommendSubjectService.listForPage(subjectName, recommendStatus, pageSize, pageNum);
        return new ResultParam().success(homeBrandList);
    }
}
