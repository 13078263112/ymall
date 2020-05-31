package com.ywc.ymall.controller.sms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.ymall.sms.entity.HomeAdvertise;
import com.ywc.ymall.sms.service.HomeAdvertiseService;
import com.ywc.ymall.to.ResultParam;
import com.ywc.ymall.vo.PageInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 嘟嘟~
 * @date 2020/6/1 1:54
 */
@CrossOrigin
@RestController
@Api(tags = "SmsHomeAdvertiseController", description = "首页轮播广告管理")
@RequestMapping("/home/advertise")
public class SmsHomeAdvertiseController {
    @Reference
    HomeAdvertiseService homeAdvertiseService;

    @ApiOperation("添加广告")
    @PostMapping(value = "/create")
    public Object create(@RequestBody HomeAdvertise advertise) {
        homeAdvertiseService.create(advertise);
        return new ResultParam().success(null);
    }

    @ApiOperation("删除广告")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestParam("ids") List<Long> ids) {
        homeAdvertiseService.deleteByIds(ids);
        return new ResultParam().success(null);
    }

    @ApiOperation("修改上下线状态")
    @RequestMapping(value = "/update/status/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object updateStatus(@PathVariable Long id, Integer status) {
        homeAdvertiseService.updateStatus(id, status);
        return new ResultParam().success(null);
    }

    @ApiOperation("获取广告详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object getItem(@PathVariable Long id) {
        HomeAdvertise advertise = homeAdvertiseService.seleteHomeAdvertiseById(id);
        return new ResultParam().success(advertise);
    }

    @ApiOperation("修改广告")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable Long id, @RequestBody HomeAdvertise advertise) {
        advertise.setId(id);
        homeAdvertiseService.updateHomeAdvertiseById(advertise);
        return new ResultParam().success(null);
    }

    @ApiOperation("分页查询广告")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(@RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "type", required = false) Integer type,
                             @RequestParam(value = "endTime", required = false) String endTime,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageInfoVo advertiseList = homeAdvertiseService.listAdvertiseForPage(name, type, endTime, pageSize, pageNum);
        return new ResultParam().success(advertiseList);
    }
}
