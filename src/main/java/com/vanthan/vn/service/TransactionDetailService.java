package com.vanthan.vn.service;

import com.vanthan.vn.dto.BaseResponse;

import com.vanthan.vn.model.TransactionDetail;
import org.springframework.stereotype.Service;


public interface TransactionDetailService {
    BaseResponse<TransactionDetail> findById(int id);
}
