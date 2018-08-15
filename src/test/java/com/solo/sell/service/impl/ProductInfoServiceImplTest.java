package com.solo.sell.service.impl;

import com.solo.sell.dto.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl service;

    @Test
    public void findById() {
        ProductInfo info = service.findById("haha123");
        Assert.assertEquals("haha123", info.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> all = service.findUpAll();
        Assert.assertNotEquals(0, all.size());
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<ProductInfo> productInfoPage = service.findAll(pageRequest);
        Assert.assertNotEquals(0, productInfoPage.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo info = new ProductInfo();
        info.setProductId("haha456");
        info.setProductName("皮皮虾");
        info.setProductPrice(new BigDecimal(49));
        info.setProductDescription("皮皮虾我们走");
        info.setProductIcon("http://abc.png");
        info.setProductStatus(0);
        info.setProductStock(99);
        info.setCategoryType(4);
        ProductInfo save = service.save(info);
        Assert.assertNotNull(save);
    }
}