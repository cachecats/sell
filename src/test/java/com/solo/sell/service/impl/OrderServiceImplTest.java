package com.solo.sell.service.impl;

import com.solo.sell.dataobject.OrderDetail;
import com.solo.sell.dto.CartDTO;
import com.solo.sell.dto.OrderDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.Null;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    OrderServiceImpl orderService;

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("张三");
        orderDTO.setBuyerAddress("天津市西青区");
        orderDTO.setBuyerPhone("13344545657");
        orderDTO.setBuyerOpenid("ew3euwhd7sjw9diwkq");

        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(new OrderDetail("haha123", 2));
        orderDetails.add(new OrderDetail("haha456", 3));
        orderDTO.setOrderDetailList(orderDetails);
        orderService.create(orderDTO);
    }

    @Test
    public void findByOrderId() {
    }

    @Test
    public void findList() {
    }

    @Test
    public void cancel() {
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }
}