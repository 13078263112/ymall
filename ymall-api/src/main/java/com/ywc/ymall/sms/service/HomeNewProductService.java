package com.ywc.ymall.sms.service;

import com.ywc.ymall.sms.entity.HomeNewProduct;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ywc.ymall.vo.PageInfoVo;

import java.util.List;

/**
 * <p>
 * 新鲜好物表 服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
public interface HomeNewProductService extends IService<HomeNewProduct> {

    PageInfoVo listNewProductForPage(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum);

    void create(List<HomeNewProduct> homeBrandList);

    void updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    void updateSort(Long id, Integer sort);

    void deleteByIds(List<Long> ids);
}
