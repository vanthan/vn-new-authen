package com.vanthan.vn.service;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.OrderForm;
import com.vanthan.vn.dto.OrderResult;
import com.vanthan.vn.model.TransactionDetail;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderService {
    BaseResponse<OrderResult> createOrder(OrderForm form, HttpServletRequest request);
    BaseResponse<OrderResult> getOrder(int orderId);
}
