package com.ywc.ymall.controller.ums.vo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.ymall.to.ResultParam;
import com.ywc.ymall.ums.entity.MemberLevel;
import com.ywc.ymall.ums.service.MemberLevelService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 嘟嘟~
 * @date 2020/4/21 23:09
 */
@CrossOrigin
@Api(tags = "UmsMemberController", description = "会员服务管理")
@RestController
public class UmsMemberController {

    @Reference
    MemberLevelService memberLevelService;

    @GetMapping("/memberLevel/list")
    public Object memberLevel(@RequestParam(value = "defaultStatus",defaultValue = "0") Integer defaultStatus){
        return new ResultParam().success(memberLevelService.getMemberLevelByStatus(defaultStatus));
    }

}
