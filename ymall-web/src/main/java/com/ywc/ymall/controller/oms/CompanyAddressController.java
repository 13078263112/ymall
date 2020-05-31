package com.ywc.ymall.controller.oms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.ymall.oms.entity.CompanyAddress;
import com.ywc.ymall.oms.service.CompanyAddressService;
import com.ywc.ymall.to.ResultParam;
import com.ywc.ymall.vo.PageInfoVo;
import com.ywc.ymall.vo.oms.OmsOrderQueryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 嘟嘟~
 * @date 2020/5/30 17:08
 */
@CrossOrigin
@RestController
@Api(tags = "CompanyAddressController",description = "发货地址管理")
@RequestMapping("/companyAddress")
public class CompanyAddressController {
    @Reference
    CompanyAddressService companyAddressService;
    @ApiOperation("查询发货地址")
    @GetMapping(value = "/list")
    public Object getList() {
      List<CompanyAddress> list= companyAddressService.getList();
        return new ResultParam().success(list);
    }
}
