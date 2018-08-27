package com.solo.sell.controller;

import com.solo.sell.VO.ResultVO;
import com.solo.sell.convertor.OrderForm2OrderDTO;
import com.solo.sell.dto.OrderDTO;
import com.solo.sell.enums.ResultEnum;
import com.solo.sell.exception.SellException;
import com.solo.sell.form.OrderForm;
import com.solo.sell.service.OrderService;
import com.solo.sell.utils.ResultVOUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/buyer/order")
@Api("买家订单服务")
public class BuyerOrderController {

    @Autowired
    OrderService orderService;

    //创建订单
    @PostMapping(value = "/create")
    @ApiOperation(value = "创建订单")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数错误，orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAMS_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        //将 OrderForm 转化为 OrderDTO
        OrderDTO orderDTO = OrderForm2OrderDTO.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车为空，orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        //创建订单
        OrderDTO result = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());
        return ResultVOUtils.success(map);
    }

    //订单列表
    @GetMapping("/list")
    @ApiOperation("查询订单列表")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String opendid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size){
        if (StringUtils.isEmpty(opendid)){
            log.error("【订单列表】openid不能为空");
            throw new SellException(ResultEnum.PARAMS_ERROR);
        }
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OrderDTO> list = orderService.findList(opendid, pageRequest);
        return ResultVOUtils.success(list.getContent());
    }

    //订单详情
    @GetMapping("/detail")
    @ApiOperation("查看订单详情")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                 @RequestParam("orderId") String orderId){
        //TODO 不安全，待优化
        OrderDTO orderDTO = orderService.findByOrderId(orderId);
        return ResultVOUtils.success(orderDTO);
    }

    //取消订单
    @PostMapping("/cancel")
    @ApiOperation("取消订单")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId){
        OrderDTO orderDTO = orderService.findByOrderId(orderId);
        OrderDTO result = orderService.cancel(orderDTO);
        return ResultVOUtils.success();
    }

}
