package com.solo.sell.repository;

import com.solo.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void add(){
        ProductInfo info = new ProductInfo();
        info.setProductId("haha123");
        info.setProductName("酸菜鱼");
        info.setProductPrice(new BigDecimal(36));
        info.setProductDescription("好吃的酸菜鱼，不可错过");
        info.setProductIcon("http://abc.png");
        info.setProductStatus(0);
        info.setProductStock(10);
        info.setCategoryType(3);
        ProductInfo result = repository.save(info);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne(){
        ProductInfo info = repository.findById("haha123").get();
        Assert.assertEquals("haha123", info.getProductId());
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> list = repository.findByProductStatus(0);
        Assert.assertNotEquals(0, list.size());
    }
}