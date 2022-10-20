package com.vanthan.vn.controller;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.OrderForm;
import com.vanthan.vn.jwt.AuthTokenFilter;
import com.vanthan.vn.jwt.JwtUtils;
import com.vanthan.vn.model.TransactionDetail;
import com.vanthan.vn.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class OrderController {

    private final OrderService orderService;

//    private final JwtUtils jwtUtils;

//    private final AuthTokenFilter authTokenFilter;

    @Autowired
    public OrderController(OrderService orderService, JwtUtils jwtUtils, AuthTokenFilter authTokenFilter) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/orders")
    public ResponseEntity<BaseResponse<TransactionDetail>> createOrder(@RequestBody OrderForm form, HttpServletRequest request) {
        try {
            // get username from http request
            return ResponseEntity.ok(orderService.createOrder(form,request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse<>(null, e.getMessage()));
        }
    }
}

