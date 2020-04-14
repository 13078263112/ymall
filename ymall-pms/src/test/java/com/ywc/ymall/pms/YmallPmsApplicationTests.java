package com.ywc.ymall.pms;

import com.ywc.ymall.pms.entity.Product;
import com.ywc.ymall.pms.mapper.ProductCategoryMapper;
import com.ywc.ymall.pms.service.ProductCategoryService;
import com.ywc.ymall.pms.service.ProductService;
import com.ywc.ymall.to.PmsProductCategoryWithChildrenItem;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class YmallPmsApplicationTests {
    @Autowired
    ProductService productService;
    @Autowired
    ProductCategoryMapper productCategoryMapper;
    @Test
    void contextLoads() {
        Product byId = productService.getById(1);
        System.out.println(byId);
    }
    @Test
    void contextLoad1() {
        List<PmsProductCategoryWithChildrenItem> pmsProductCategoryWithChildrenItems = productCategoryMapper.listWithChildren(0);
        System.out.println(pmsProductCategoryWithChildrenItems.iterator().next());
    }

}
