package com.ywc.ymall.controller.sms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.ymall.sms.entity.FlashPromotion;
import com.ywc.ymall.sms.service.FlashPromotionService;
import com.ywc.ymall.to.ResultParam;
import com.ywc.ymall.vo.PageInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author 嘟嘟~
 * @date 2020/5/31 1:46
 */
@CrossOrigin
@RestController
@Api(tags = "SmsFlashPromotionController", description = "限时购活动管理")
@RequestMapping("/flash")
public class SmsFlashPromotionController {
    @Reference
    private FlashPromotionService flashPromotionService;
    @ApiOperation("根据活动名称分页查询")
    @GetMapping(value = "/list")
    public Object getItem(@RequestParam(value = "keyword", required = false) String keyword,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageInfoVo pageInfoVo = flashPromotionService.listflashPromotionForPage(keyword, pageSize, pageNum);
        return new ResultParam().success(pageInfoVo);
    }
    @ApiOperation("添加活动")
    @PostMapping(value = "/create")
    public Object create(@RequestBody FlashPromotion flashPromotion) {
        flashPromotionService.createFlashPromotion(flashPromotion);
        return new ResultParam().success(null);
    }
    @ApiOperation("删除活动信息")
    @PostMapping(value = "/delete/{id}")
    public Object delete(@PathVariable Long id) {
        flashPromotionService.deleteById(id);
        return new ResultParam().success(null);
    }
    @ApiOperation("修改上下线状态")
    @RequestMapping(value = "/update/status/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable Long id, Integer status) {

        flashPromotionService.updateStatus(id, status);

        return new ResultParam().success(null);
    }
}
