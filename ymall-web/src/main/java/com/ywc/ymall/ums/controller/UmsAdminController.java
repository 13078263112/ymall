package com.ywc.ymall.ums.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.ymall.to.ResultParam;
import com.ywc.ymall.ums.entity.Admin;
import com.ywc.ymall.ums.service.AdminService;
import com.ywc.ymall.ums.vo.LoginParam;
import com.ywc.ymall.utils.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 嘟嘟~
 * @date 2020/3/22 2:11
 */
@Api(tags = "AdminController", description = "后台用户管理")
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class UmsAdminController {
    @Reference
    AdminService adminService;
    @Value("${ymall.jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${ymall.jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @ApiOperation(value = "登录以后返回token")
    @PostMapping("/login")
    public Object login(@RequestBody LoginParam loginParam, BindingResult result) {
        //去数据库登陆
        Admin admin = adminService.login(loginParam.getUsername(), loginParam.getPassword());
        //登陆成功生成token，此token携带基本用户信息，以后就不用去数据库了
        String token = jwtTokenUtil.generateToken(admin);
        if (token == null) {
            return new ResultParam().validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return new ResultParam().success(tokenMap);
    }
    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping("/info")
    public Object getAdminInfo(HttpServletRequest request) {
        String oldToken = request.getHeader(tokenHeader);
        String token = oldToken.substring(tokenHead.length());
        String userName = jwtTokenUtil.getUserNameFromToken(token);
        Admin umsAdmin = adminService.getAdminByUsername(userName);
        Map<String, Object> data = new HashMap<>();
        data.put("username", umsAdmin.getUsername());
        data.put("roles", new String[]{"TEST"});
        data.put("icon", umsAdmin.getIcon());
        return new ResultParam().success(data);
    }
}
