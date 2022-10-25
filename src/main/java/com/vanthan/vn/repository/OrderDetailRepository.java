package com.vanthan.vn.repository;

import com.vanthan.vn.model.OrderDetail;
import com.vanthan.vn.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    Optional<OrderDetail> findById(Integer id);
}
