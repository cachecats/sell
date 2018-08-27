package com.solo.sell.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.solo.sell.dataobject.OrderDetail;
import com.solo.sell.enums.OrderStatusEnum;
import com.solo.sell.enums.PayStatusEnum;
import com.solo.sell.utils.serializer.Date2LongSerializer;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    /**
     * 订单id
     */
    @Id
    private String orderId;

    /**
     * 买家姓名
     */
    private String buyerName;

    /**
     * 买家电话
     */
    private String buyerPhone;

    /**
     * 买家地址
     */
    private String buyerAddress;

    /**
     * 买家微信openid
     */
    private String buyerOpenid;

    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态、0：新下单  1：完成  2：取消
     */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /**
     * 支付状态。0：未支付  1：已支付  2：支付失败
     */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /**
     * 创建时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    /**
     * 订单详情
     */
    private List<OrderDetail> orderDetailList;
}
