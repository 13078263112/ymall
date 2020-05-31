package com.ywc.ymall.controller.sms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.ymall.sms.entity.FlashPromotionProductRelation;
import com.ywc.ymall.sms.service.FlashPromotionProductRelationService;
import com.ywc.ymall.to.ResultParam;
import com.ywc.ymall.vo.PageInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 嘟嘟~
 * @date 2020/5/31 23:21
 */
@CrossOrigin
@RestController
@Api(tags = "SmsFlashPromotionProductRelationController", description = "限时购和商品关系管理")
@RequestMapping("/flashProductRelation")
public class SmsFlashPromotionProductRelationController {
    @Reference
    private FlashPromotionProductRelationService flashPromotionProductRelationService;

    @ApiOperation("分页查询不同场次关联及商品信息")
    @GetMapping(value = "/list")
    public Object list(@RequestParam(value = "flashPromotionId") Long flashPromotionId,
                             @RequestParam(value = "flashPromotionSessionId") Long flashPromotionSessionId,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageInfoVo pageInfoVo =flashPromotionProductRelationService.listRelationForPage(flashPromotionId, flashPromotionSessionId, pageSize, pageNum);
        return new ResultParam().success(pageInfoVo);
    }
    @ApiOperation("批量选择商品添加关联")
    @PostMapping(value = "/create")
    public Object create(@RequestBody List<FlashPromotionProductRelation> relationList) {
        flashPromotionProductRelationService.create(relationList);
        return new ResultParam().success(null);
    }
    @ApiOperation("修改关联相关信息")
    @PostMapping(value = "/update/{id}")
    public Object update(@PathVariable Long id, @RequestBody FlashPromotionProductRelation relation) {
        relation.setId(id);
        flashPromotionProductRelationService.updateFlashPromotionProductRelationById(relation);
        return new ResultParam().success(null);
    }
    @ApiOperation("删除关联")
    @PostMapping(value = "/delete/{id}")
    public Object delete(@PathVariable Long id) {
        flashPromotionProductRelationService.deleteById(id);
        return new ResultParam().success(null);
    }
}
