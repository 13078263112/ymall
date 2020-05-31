package com.ywc.ymall.controller.sms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.ymall.sms.entity.FlashPromotionSession;
import com.ywc.ymall.sms.service.FlashPromotionSessionService;
import com.ywc.ymall.to.ResultParam;
import com.ywc.ymall.vo.PageInfoVo;
import com.ywc.ymall.vo.oms.FlashPromotionSessionParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 嘟嘟~
 * @date 2020/5/31 2:07
 */
@CrossOrigin
@RestController
@Api(tags = "SmsFlashPromotionSessionController", description = "限时购场次管理")
@RequestMapping("/flashSession")
public class SmsFlashPromotionSessionController {
    @Reference
    private FlashPromotionSessionService flashPromotionSessionService;
    @ApiOperation("获取全部场次")
    @GetMapping(value = "/list")
    public Object list() {
        List<FlashPromotionSession> promotionSessionList = flashPromotionSessionService.getList();
        return new ResultParam().success(promotionSessionList);
    }
    @ApiOperation("添加场次")
    @PostMapping(value = "/create")
    public Object create(@RequestBody FlashPromotionSession promotionSession) {
        flashPromotionSessionService.create(promotionSession);
        return new ResultParam().success(null);
    }
    @ApiOperation("修改启用状态")
    @PostMapping(value = "/update/status/{id}")
    public Object updateStatus(@PathVariable Long id, Integer status) {
        flashPromotionSessionService.updateStatus(id, status);
        return new ResultParam().success(null);
    }
    @ApiOperation("修改场次")
    @PostMapping(value = "/update/{id}")
    public Object update(@PathVariable Long id,
                         @RequestBody FlashPromotionSession promotionSession) {
        promotionSession.setId(id);
        flashPromotionSessionService.updateFlashPromotionSessionById(promotionSession);
        return new ResultParam().success(null);
    }
    @ApiOperation("删除场次")
    @PostMapping(value = "/delete/{id}")
    public Object delete(@PathVariable Long id) {
        flashPromotionSessionService.deleteById(id);
        return new ResultParam().success(null);
    }
    @ApiOperation("获取全部可选场次及其数量")
    @GetMapping(value = "/selectList")
    public Object selectList(Long flashPromotionId) {
        List<FlashPromotionSessionParam> flashPromotionSessionParams = flashPromotionSessionService.selectListForPage(flashPromotionId);
        return new ResultParam().success(flashPromotionSessionParams);
    }
}
