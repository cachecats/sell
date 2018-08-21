package com.solo.sell.controller;

import com.solo.sell.VO.ProductInfoVO;
import com.solo.sell.VO.ProductVO;
import com.solo.sell.VO.ResultVO;
import com.solo.sell.dataobject.ProductCategory;
import com.solo.sell.dataobject.ProductInfo;
import com.solo.sell.service.ProductCategoryService;
import com.solo.sell.service.ProductInfoService;
import com.solo.sell.utils.ResultVOUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
@Api("买家端获取商品")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService categoryService;

    @ApiOperation(value = "获取所有上架商品", notes = "获取所有上架商品,下架商品除外")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultVO list() {

        // 1.查询所有上架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();

        // 2.查询类目（一次性查询）
        //用 java8 的特性获取到上架商品的所有类型
        List<Integer> categoryTypes = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypes);

        List<ProductVO> productVOList = new ArrayList<>();
        //数据拼装
        for (ProductCategory category : productCategoryList) {
            ProductVO productVO = new ProductVO();
            //属性拷贝
            BeanUtils.copyProperties(category, productVO);
            //把类型匹配的商品添加进去
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(category.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtils.success(productVOList);
    }
}
