package com.ywc.ymall.controller.pms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.ymall.pms.entity.SkuStock;
import com.ywc.ymall.pms.service.SkuStockService;
import com.ywc.ymall.to.ResultParam;
import com.ywc.ymall.vo.PageInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**sku商品库存管理
 * @author 嘟嘟~
 * @date 2020/4/15 13:18
 */
@CrossOrigin
@RestController
@Api(tags = "PmsSkuStockController", description = "sku商品库存管理")
@RequestMapping("/sku")
public class PmsSkuStockController {
    @Reference
    private SkuStockService skuStockService;

    @ApiOperation("根据商品编号及编号模糊搜索sku库存")
    @GetMapping(value = "/{pid}")
    public Object getList(@PathVariable Long pid, @RequestParam(value = "keyword",required = false) String keyword) {
        //TODO 根据商品编号及编号模糊搜索sku库存
       List<SkuStock> list = skuStockService.querySkuByIdByName(pid,keyword);
        return new ResultParam().success(list);
    }
    @ApiOperation("批量更新库存信息")
    @PostMapping(value ="/update/{pid}")
    @ResponseBody
    public Object update(@PathVariable Long pid,@RequestBody List<SkuStock> skuStockList){
        int i = skuStockService.updateSkuByPid(skuStockList);
        return new ResultParam().success(i);
    }
}
