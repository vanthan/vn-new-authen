package com.vanthan.vn.repository;

import com.vanthan.vn.model.OrderLine;
import com.vanthan.vn.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
}
