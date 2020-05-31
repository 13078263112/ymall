package com.ywc.ymall.pms.service;

import com.ywc.ymall.pms.entity.Brand;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
public interface BrandService extends IService<Brand> {

    Map<String,Object> pageBrand(String keyword, Integer pageNum, Integer pageSize);

    Brand queryById(Long id);

    boolean updateBrandById(Brand brand);

    boolean deleteById(Long id);

    void deleteByIds(List<Long> ids);

    void updateShowStatus(List<Long> ids, Integer showStatus);

    void updateFactoryStatus(List<Long> ids, Integer factoryStatus);
}
