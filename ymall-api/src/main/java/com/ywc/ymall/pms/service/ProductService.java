package com.ywc.ymall.pms.service;

import com.ywc.ymall.pms.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ywc.ymall.vo.PageInfoVo;
import com.ywc.ymall.vo.PmsProductParam;
import com.ywc.ymall.vo.PmsProductQueryParam;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
public interface ProductService extends IService<Product> {

    PageInfoVo pageProduct(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum);

    boolean updateDeleteStatus(List<Long> ids, Integer deleteStatus);

    boolean updatePublishStatus(List<Long> ids, Integer publishStatus);

    boolean updateNewStatus(List<Long> ids, Integer newStatus);

    boolean updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    Product getUpdateInfo(Long id);

    void create(PmsProductParam productParam);
    Product  productInfo(Long id);

    List<Product> queryAllByRecommand();


    void updateProduct(Long id, PmsProductParam productParam);
}
