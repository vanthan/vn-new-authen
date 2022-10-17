package com.vanthan.vn.service;

import com.vanthan.vn.dto.OrderForm;
import com.vanthan.vn.dto.OrderResult;

public interface OrderService {
    OrderResult createOrder(OrderForm form);
}
