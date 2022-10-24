package com.vanthan.vn.service;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.OrderForm;
import com.vanthan.vn.dto.OrderResult;

import javax.servlet.http.HttpServletRequest;

public interface OrderService {
    BaseResponse<OrderResult> createOrder(OrderForm form, HttpServletRequest request);

}
