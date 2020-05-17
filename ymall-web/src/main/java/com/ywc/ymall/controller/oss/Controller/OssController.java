package com.ywc.ymall.controller.oss.Controller;

import com.ywc.ymall.controller.oss.compent.OssCompent;
import com.ywc.ymall.to.ResultParam;
import com.ywc.ymall.vo.OssPolicyResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 嘟嘟~
 * @date 2020/5/2 11:53
 */
@CrossOrigin
@Controller
@Api(tags = "OssController",description = "Oss管理")
@RequestMapping("/aliyun/oss")
public class OssController {
    @Autowired
    private OssCompent ossComponent;

    @ApiOperation(value = "oss上传签名生成")
    @GetMapping(value = "/policy")
    @ResponseBody
    public Object policy() {
        OssPolicyResult result = ossComponent.policy();
        return new ResultParam().success(result);
    }
}
