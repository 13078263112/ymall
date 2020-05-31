package com.ywc.ymall.controller.pms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.ymall.controller.pms.vo.PmsBrandParam;
import com.ywc.ymall.pms.entity.Brand;
import com.ywc.ymall.pms.service.BrandService;
import com.ywc.ymall.to.ResultParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 品牌功能
 * @author 嘟嘟~
 * @date 2020/4/14 17:33
 */
@CrossOrigin
@RestController
@Api(tags = "PmsBrandController",description = "商品品牌管理")
@RequestMapping("/brand")
public class PmsBrandController {
    @Reference
    BrandService brandService;
    @ApiOperation(value = "根据品牌名称分页获取品牌列表")
    @GetMapping(value = "/list")
    public Object getList(@RequestParam(value = "keyword", required = false) String keyword,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {

        // 根据品牌名称分页获取品牌列表
        Map<String,Object> brandPageInfo =  brandService.pageBrand(keyword,pageNum,pageSize);

        return new ResultParam().success(brandPageInfo);
    }
    @ApiOperation(value = "根据编号查询品牌信息")
    @GetMapping(value = "/{id}")
    public Object getItem(@PathVariable("id") Long id) {

        //TODO 根据编号查询品牌信息
      Brand brand= brandService.queryById(id);
        return new ResultParam().success(brand);
    }
    @ApiOperation(value = "更新品牌")
    @PostMapping(value = "/update/{id}")
    public Object update(@PathVariable("id") Long id,
                         @Validated @RequestBody PmsBrandParam pmsBrandParam,
                         BindingResult result) {
        Brand brand = new Brand();
        BeanUtils.copyProperties(pmsBrandParam,brand);
        brand.setId(id);
        System.out.println(brand);
       boolean b = brandService.updateBrandById(brand);
        //TODO 更新品牌
        return new ResultParam().success(b) ;
    }
    @ApiOperation(value = "删除品牌")
    @GetMapping(value = "/delete/{id}")
    public Object delete(@PathVariable("id") Long id) {
        boolean b =  brandService.deleteById(id);

        //TODO 删除品牌

        return new ResultParam().success(b);
    }
    @ApiOperation(value = "批量删除品牌")
    @PostMapping(value = "/delete/batch")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        //TODO 批量删除品牌
            brandService.deleteByIds(ids);
        return new ResultParam().success(null);
    }

    @ApiOperation(value = "批量更新显示状态")
    @PostMapping(value = "/update/showStatus")
    public Object updateShowStatus(@RequestParam("ids") List<Long> ids,
                                   @RequestParam("showStatus") Integer showStatus) {

        //TODO 批量更新显示状态
        brandService.updateShowStatus(ids,showStatus);
        return new ResultParam().success(null);
    }

    @ApiOperation(value = "批量更新厂家制造商状态")
    @PostMapping(value = "/update/factoryStatus")
    public Object updateFactoryStatus(@RequestParam("ids") List<Long> ids,
                                      @RequestParam("factoryStatus") Integer factoryStatus) {

        //TODO 批量更新厂家制造商状态
        brandService.updateFactoryStatus(ids,factoryStatus);
        return new ResultParam().success(null);
    }
}
