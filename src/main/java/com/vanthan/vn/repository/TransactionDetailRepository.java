//package com.vanthan.vn.repository;
//
//import com.vanthan.vn.model.OrderResult;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//
//@Repository
//public interface TransactionDetailRepository extends JpaRepository<OrderResult, Integer> {
//    @Query("select t from OrderResult t where t.orderId = :#{#orderId}")
//    List<OrderResult> findByOrderId(@Param("orderId") int orderId);
//}
