package com.ywc.ymall.sms.service;

import com.ywc.ymall.sms.entity.HomeRecommendProduct;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ywc.ymall.vo.PageInfoVo;

import java.util.List;

/**
 * <p>
 * 人气推荐商品表 服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
public interface HomeRecommendProductService extends IService<HomeRecommendProduct> {

    List<HomeRecommendProduct> queryAll();

    PageInfoVo listrecommendProductForPage(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum);

    void create(List<HomeRecommendProduct> homeBrandList);

    void updateSort(Long id, Integer sort);

    void deleteByIds(List<Long> ids);

    void updateRecommendStatus(List<Long> ids, Integer recommendStatus);
}
