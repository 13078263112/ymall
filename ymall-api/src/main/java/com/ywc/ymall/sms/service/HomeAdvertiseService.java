package com.ywc.ymall.sms.service;

import com.ywc.ymall.sms.entity.HomeAdvertise;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页轮播广告表 服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
public interface HomeAdvertiseService extends IService<HomeAdvertise> {

    List<HomeAdvertise> queryHomeAdvertiseAll();
}
