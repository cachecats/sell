package com.solo.sell.repository;

import com.solo.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    /**
     * 通过买家的openid查询订单
     * @param openId
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByBuyerOpenid(String openId, Pageable pageable);
}
