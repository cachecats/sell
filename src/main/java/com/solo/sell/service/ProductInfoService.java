package com.solo.sell.service;

import com.solo.sell.dto.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {

    ProductInfo findById(String id);

    /**
     * 查询上架的产品
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询所有商品 带分页
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存

    //减库存


}
