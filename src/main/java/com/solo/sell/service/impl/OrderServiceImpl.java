package com.solo.sell.service.impl;

import com.solo.sell.dataobject.OrderDetail;
import com.solo.sell.dataobject.OrderMaster;
import com.solo.sell.dataobject.ProductInfo;
import com.solo.sell.dto.CartDTO;
import com.solo.sell.dto.OrderDTO;
import com.solo.sell.enums.OrderStatusEnum;
import com.solo.sell.enums.PayStatusEnum;
import com.solo.sell.enums.ResultEnum;
import com.solo.sell.exception.SellException;
import com.solo.sell.repository.OrderDetailRepository;
import com.solo.sell.repository.OrderMasterRepository;
import com.solo.sell.service.OrderService;
import com.solo.sell.service.ProductInfoService;
import com.solo.sell.utils.KeyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    ProductInfoService productInfoService;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtils.genUniqueKey();
        //商品总价
        BigDecimal totalPrice = new BigDecimal(BigInteger.ZERO);
        //1. 查询（库存，价格）
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            //获取商品信息
            ProductInfo productInfo = productInfoService.findById(orderDetail.getProductId());
            //商品不存在则抛出异常
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2. 计算总价
            totalPrice = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(totalPrice);
            //3. 将详情存入数据库
            //注意顺序，拷贝之后null值也会被拷贝，所以在拷贝之后再赋值
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtils.genUniqueKey());
            orderDetailRepository.save(orderDetail);
        }
        //4. 将订单主表写入数据库
        OrderMaster orderMaster = new OrderMaster();
        //注意顺序，拷贝之后null值也会被拷贝，所以在拷贝之后再赋值
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(totalPrice);
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMasterRepository.save(orderMaster);
        //5. 减库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
            new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOList);

        return orderDTO;
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
