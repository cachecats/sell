package com.solo.sell.service.impl;

import com.solo.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    ProductCategoryServiceImpl service;

    @Test
    public void findOne() {
        ProductCategory one = service.findOne(1);
        Assert.assertEquals(new Integer(1), one.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> list = service.findAll();
        Assert.assertNotEquals(0, list.size());
    }

    @Test
    public void save() {
        ProductCategory cate = service.save(new ProductCategory("热销榜", 5));
        Assert.assertNotNull(cate);
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> list = service.findByCategoryTypeIn(Arrays.asList(3, 4, 5, 6));
        Assert.assertNotEquals(0, list.size());
    }
}