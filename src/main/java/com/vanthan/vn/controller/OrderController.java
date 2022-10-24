package com.vanthan.vn.controller;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.OrderForm;
import com.vanthan.vn.dto.OrderResult;
import com.vanthan.vn.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/orders")
    public ResponseEntity<BaseResponse<OrderResult>> createOrder(@RequestBody OrderForm form, HttpServletRequest request) {
        try {
            // get username from http request
            return ResponseEntity.ok(orderService.createOrder(form,request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse<>(null, e.getMessage()));
        }
    }

    @GetMapping(value = "/orders/{id}")
    public ResponseEntity<BaseResponse<OrderResult>> getOrder(@PathVariable int id) {
        //return ResponseEntity.ok(orderService.);

        // summarize order info from order, product, order detail,... user
        return null;

    }
}

