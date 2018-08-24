package com.solo.sell.service.impl;

import com.solo.sell.dataobject.OrderDetail;
import com.solo.sell.dto.CartDTO;
import com.solo.sell.dto.OrderDTO;
import com.solo.sell.enums.OrderStatusEnum;
import com.solo.sell.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.Null;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
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
        orderDetails.add(new OrderDetail("haha123", 1));
        orderDetails.add(new OrderDetail("haha456", 1));
        orderDTO.setOrderDetailList(orderDetails);
        OrderDTO result = orderService.create(orderDTO);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() {
        OrderDTO dto = orderService.findByOrderId("1535036271169568507");
        log.info("【查询一个】:{}", dto);
        Assert.assertNotNull(dto);
    }

    @Test
    public void findList() {
        Page<OrderDTO> page = orderService.findList("ew3euwhd7sjw9diwkq", PageRequest.of(0, 3));
        log.info("【查询列表】：{}", page.getContent());
        Assert.assertNotEquals(0, page.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.cancel(orderService.findByOrderId("1535036271169568507"));
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), orderDTO.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.finish(orderService.findByOrderId("1535036271169568507"));
        Assert.assertEquals(OrderStatusEnum.FINISH.getCode(), orderDTO.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.paid(orderService.findByOrderId("1535036271169568507"));
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), orderDTO.getPayStatus());
    }
}