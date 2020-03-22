package com.ywc.ymall.ums.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.ums.entity.Admin;
import com.ywc.ymall.ums.mapper.AdminMapper;
import com.ywc.ymall.ums.service.AdminService;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Component
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Override
    public Admin login(String username, String password) {
        return this.baseMapper.selectOne(new QueryWrapper<Admin>().eq("username",username).eq("password", DigestUtils.md5DigestAsHex(password.getBytes())));
    }

    @Override
    public Admin getAdminByUsername(String userName) {
        return this.baseMapper.selectOne(new QueryWrapper<Admin>().eq("username",userName));
    }
}
