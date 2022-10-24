package com.vanthan.vn.service;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.OrderForm;
import com.vanthan.vn.dto.OrderLineForm;
import com.vanthan.vn.dto.OrderResult;
import com.vanthan.vn.model.TransactionDetail;
import org.apache.tomcat.util.json.ParseException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderService {
    BaseResponse<String> createOrder(OrderForm form, HttpServletRequest request);
}
