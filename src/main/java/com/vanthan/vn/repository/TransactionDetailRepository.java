package com.vanthan.vn.repository;

import com.vanthan.vn.model.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Integer> {
    @Query("select t from TransactionDetail t where t.orderId = :#{#orderId}")
    List<TransactionDetail> findByOrderId(@Param("orderId") int orderId);
}
