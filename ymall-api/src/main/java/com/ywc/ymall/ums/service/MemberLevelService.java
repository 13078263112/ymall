package com.ywc.ymall.ums.service;

import com.ywc.ymall.ums.entity.MemberLevel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 会员等级表 服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
public interface MemberLevelService extends IService<MemberLevel> {

    List<MemberLevel> getMemberLevelByStatus(Integer defaultStatus);
}
