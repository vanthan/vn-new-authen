package com.vanthan.vn.service;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.OrderForm;
import com.vanthan.vn.dto.OrderLineForm;
import com.vanthan.vn.dto.OrderResult;
import com.vanthan.vn.model.TransactionDetail;

import javax.servlet.http.HttpServletRequest;

public interface OrderService {
    BaseResponse<TransactionDetail> createOrder(OrderForm form, HttpServletRequest request);
}
