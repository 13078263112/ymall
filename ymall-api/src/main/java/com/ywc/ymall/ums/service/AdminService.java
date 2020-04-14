package com.ywc.ymall.ums.service;

import com.ywc.ymall.ums.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
public interface AdminService extends IService<Admin> {

    Admin login(String username, String password);

    Admin getAdminByUsername(String userName);
}
