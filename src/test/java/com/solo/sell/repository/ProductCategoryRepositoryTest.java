package com.solo.sell.repository;

import com.solo.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    @Transactional
    public void add() {
        ProductCategory category = new ProductCategory("女生最爱", 1);
        ProductCategory save = repository.save(category);
        Assert.assertNotNull(save);
    }

    @Test
    public void findOne() {
        ProductCategory productCategory = repository.findById(1).get();
        Assert.assertNotNull(productCategory);
    }

    @Test
    @Transactional
    public void update() {
        ProductCategory category = repository.findById(1).get();
        category.setCategoryType(4);
        ProductCategory save = repository.save(category);
        Assert.assertNotNull(save);
    }

    /**
     * 传入类型列表，查询包含列表中类型的所有数据
     */
    @Test
    public void findByCategoryType() {
        List<Integer> types = Arrays.asList(2, 3, 4);
        List<ProductCategory> list = repository.findByCategoryTypeIn(types);
        Assert.assertNotEquals(0, list.size());
    }
}