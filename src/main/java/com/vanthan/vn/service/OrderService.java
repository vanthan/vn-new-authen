package com.vanthan.vn.service;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.OrderForm;
import com.vanthan.vn.dto.OrderResult;

import javax.servlet.http.HttpServletRequest;

public interface OrderService {
    BaseResponse<String> createOrder(OrderForm form, String token);
    BaseResponse<OrderResult> getOrder(int orderId);
}
