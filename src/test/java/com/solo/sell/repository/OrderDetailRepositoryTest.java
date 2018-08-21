package com.solo.sell.repository;

import com.solo.sell.dataobject.OrderDetail;
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
public class OrderDetailRepositoryTest {

    @Autowired
    OrderDetailRepository repository;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("111111");
        orderDetail.setOrderId("1234567");
        orderDetail.setProductIcon("http://xxxxxx.png");
        orderDetail.setProductId("qwert");
        orderDetail.setProductName("紫菜蛋花汤");
        orderDetail.setProductPrice(new BigDecimal(12.0));
        orderDetail.setProductQuantity(10);

        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> list = repository.findByOrderId("1234567");
        Assert.assertNotEquals(0, list.size());

    }
}