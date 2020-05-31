package com.ywc.ymall.controller.cms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.ymall.cms.entity.Subject;
import com.ywc.ymall.cms.service.SubjectService;
import com.ywc.ymall.to.ResultParam;
import com.ywc.ymall.vo.PageInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 嘟嘟~
 * @date 2020/4/16 14:09
 */
@CrossOrigin
@RestController
@Api(tags = "CmsSubjectController", description = "商品专题管理")
@RequestMapping("/subject")
public class CmsSubjectController {
    @Reference
    private SubjectService subjectService;

    @ApiOperation("获取全部商品专题")
    @GetMapping(value = "/listAll")
    public Object listAll() {
        return new ResultParam().success(subjectService.listAll());
    }
    @ApiOperation(value = "根据专题名称分页获取专题")
    @GetMapping(value = "/list")
    public Object getList(@RequestParam(value = "keyword", required = false) String keyword,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        PageInfoVo pageInfoVo= subjectService.listForPage(keyword,pageNum,pageSize);
        return new ResultParam().success(pageInfoVo);
    }
}
