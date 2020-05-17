package com.ywc.ymall.pms.service;

import com.ywc.ymall.pms.entity.SkuStock;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ywc.ymall.vo.PageInfoVo;

import java.util.List;

/**
 * <p>
 * sku的库存 服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
public interface SkuStockService extends IService<SkuStock> {

    List<SkuStock> querySkuByIdByName(Long pid, String keyword);

    int updateSkuByPid(List<SkuStock> skuStockList);
}
