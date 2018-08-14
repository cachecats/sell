package com.solo.sell.repository;

import com.solo.sell.dto.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer>{

    /**
     * 传入类型列表，查询包含列表中类型的所有数据
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> types);
}
