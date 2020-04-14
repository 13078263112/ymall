package com.ywc.ymall.controller.pms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.ymall.pms.service.ProductService;
import com.ywc.ymall.to.ResultParam;
import com.ywc.ymall.vo.PageInfoVo;
import com.ywc.ymall.vo.PmsProductQueryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**商品管理
 * @author 嘟嘟~
 * @date 2020/4/14 17:46
 */
@CrossOrigin
@RestController
@Api(tags = "PmsProductController", description = "商品管理")
@RequestMapping("/product")
public class PmsProductController {
    @Reference
    ProductService productService;

    @ApiOperation("查询商品")
    @GetMapping(value = "/list")
    public Object getList(PmsProductQueryParam productQueryParam,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {


        //TODO 分页查询商品

        PageInfoVo pageInfoVo = productService.pageProduct(productQueryParam, pageSize, pageNum);

        return new ResultParam().success(pageInfoVo);
    }
}
