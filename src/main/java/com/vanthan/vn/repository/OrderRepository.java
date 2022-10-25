package com.vanthan.vn.repository;

import com.vanthan.vn.model.Order;
import com.vanthan.vn.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
