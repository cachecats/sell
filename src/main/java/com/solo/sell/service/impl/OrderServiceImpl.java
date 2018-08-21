package com.solo.sell.service.impl;

import com.solo.sell.dataobject.OrderDetail;
import com.solo.sell.dataobject.ProductInfo;
import com.solo.sell.dto.OrderDTO;
import com.solo.sell.enums.ResultEnum;
import com.solo.sell.exception.SellException;
import com.solo.sell.repository.OrderDetailRepository;
import com.solo.sell.service.OrderService;
import com.solo.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Autowired
    ProductInfoService productInfoService;
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        //商品总价
        BigDecimal totalPrice = new BigDecimal(BigInteger.ZERO);
        //1. 查询（库存，价格）
        for(OrderDetail orderDetail : orderDTO.getOrderDetailList()){
            //获取商品信息
            ProductInfo productInfo = productInfoService.findById(orderDetail.getProductId());
            //商品不存在则抛出异常
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2. 计算总价
            totalPrice = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(totalPrice);
            // 将详情存入数据库



            orderDetailRepository.save(orderDetail);
        }

        //3. 写入数据库（订单详情，订单主表）

        //4. 减库存
        return null;
    }

    @Override
    public OrderDTO findByOrderId(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
