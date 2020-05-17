package com.ywc.ymall.controller.cms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.ymall.cms.entity.PrefrenceArea;
import com.ywc.ymall.cms.service.PrefrenceAreaService;
import com.ywc.ymall.to.ResultParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**商品优选管理
 * @author 嘟嘟~
 * @date 2020/4/16 14:04
 */
@CrossOrigin
@RestController
@Api(tags = "CmsPrefrenceAreaController", description = "商品优选管理")
@RequestMapping("/prefrenceArea")
public class CmsPrefrenceAreaController {
    @Reference
    private PrefrenceAreaService prefrenceAreaService;

    @ApiOperation("获取所有商品优选")
    @GetMapping(value = "/listAll")
    public Object listAll() {
        return new ResultParam().success(prefrenceAreaService.listAll());
    }
}
