package com.vanthan.vn.service.impl;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.model.TransactionDetail;
import com.vanthan.vn.repository.TransactionDetailRepository;
import com.vanthan.vn.service.TransactionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionDetailImp implements TransactionDetailService {
    private final TransactionDetailRepository transactionDetailRepository;

    @Autowired
    public TransactionDetailImp(TransactionDetailRepository transactionDetailRepository) {
        this.transactionDetailRepository = transactionDetailRepository;
    }


    @Override
    public BaseResponse<TransactionDetail> findById(int id) {
        BaseResponse response = new BaseResponse();
        response.setBody(transactionDetailRepository.findById(id));
        return response;
    }
}
