package com.solo.sell.controller;

import com.solo.sell.VO.ResultVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @RequestMapping("/list")
    public ResultVO list(){
        ResultVO result = new ResultVO();
        return result;
    }
}
