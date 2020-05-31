package com.ywc.ymall.sms.service;

import com.ywc.ymall.sms.entity.HomeBrand;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ywc.ymall.vo.PageInfoVo;

import java.util.List;

/**
 * <p>
 * 首页推荐品牌表 服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
public interface HomeBrandService extends IService<HomeBrand> {

    PageInfoVo listBrandForPage(String brandName, Integer recommendStatus, Integer pageSize, Integer pageNum);

    void updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    void updateSort(Long id, Integer sort);

    void deleteByIds(List<Long> ids);

    void create(List<HomeBrand> homeBrandList);
}
