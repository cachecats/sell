package com.solo.sell.convertor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.solo.sell.dataobject.OrderDetail;
import com.solo.sell.dto.OrderDTO;
import com.solo.sell.enums.ResultEnum;
import com.solo.sell.exception.SellException;
import com.solo.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class OrderForm2OrderDTO {

    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        //格式化json字符串
        List<OrderDetail> orderDetailList;
        try {
            Gson gson = new Gson();
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        }catch (Exception e){
            log.error("【格式转化错误】string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAMS_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
