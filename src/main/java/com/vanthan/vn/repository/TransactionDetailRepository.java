package com.vanthan.vn.repository;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.model.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Integer> {
    BaseResponse<TransactionDetail> findById(int id);
}
