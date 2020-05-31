package com.ywc.ymall.sms.service;

import com.ywc.ymall.sms.entity.HomeAdvertise;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ywc.ymall.vo.PageInfoVo;

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

    void create(HomeAdvertise advertise);

    void deleteByIds(List<Long> ids);

    void updateStatus(Long id, Integer status);

    HomeAdvertise seleteHomeAdvertiseById(Long id);

    void updateHomeAdvertiseById(HomeAdvertise advertise);

    PageInfoVo listAdvertiseForPage(String name, Integer type, String endTime, Integer pageSize, Integer pageNum);
}
