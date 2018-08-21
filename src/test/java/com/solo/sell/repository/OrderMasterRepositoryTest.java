package com.solo.sell.repository;

import com.solo.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    Logger logger = LoggerFactory.getLogger("OrderMasterRepositoryTest");

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerAddress("frgth嘿嘿额");
        orderMaster.setBuyerName("lisi");
        orderMaster.setBuyerOpenid("wxid12345678");
        orderMaster.setBuyerPhone("1346780765");
        orderMaster.setOrderAmount(new BigDecimal(346.0));
        orderMaster.setOrderId("12345678");

        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);

    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<OrderMaster> pages = repository.findByBuyerOpenid("wxid12345678", pageRequest);
        logger.info("total: "+pages.getTotalElements());
        Assert.assertNotEquals(0, pages.getTotalElements());
    }
}