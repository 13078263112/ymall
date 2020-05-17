package com.ywc.ymall.cms.service;

import com.ywc.ymall.cms.entity.PrefrenceArea;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 优选专区 服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
public interface PrefrenceAreaService extends IService<PrefrenceArea> {

    List<PrefrenceArea> listAll();
}
