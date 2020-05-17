package com.ywc.ymall.cms.service;

import com.ywc.ymall.cms.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 专题表 服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
public interface SubjectService extends IService<Subject> {

    List<Subject> listAll();
}
