package com.solo.sell.service.impl;

import com.solo.sell.dto.ProductCategory;
import com.solo.sell.repository.ProductCategoryRepository;
import com.solo.sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类目服务实现
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    ProductCategoryRepository repository;

    @Override
    public ProductCategory findOne(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> types) {
        return repository.findByCategoryTypeIn(types);
    }
}
