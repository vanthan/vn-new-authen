package com.vanthan.vn.controller;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.OrderForm;
import com.vanthan.vn.model.Customer;
import com.vanthan.vn.model.TransactionDetail;
import com.vanthan.vn.service.OrderService;
import com.vanthan.vn.service.TransactionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
public class OrderController {

    private final OrderService orderService;
    private final TransactionDetailService transactionDetailService;

//    private final JwtUtils jwtUtils;

//    private final AuthTokenFilter authTokenFilter;

    @Autowired
    public OrderController(OrderService orderService, TransactionDetailService transactionDetailService) {
        this.orderService = orderService;
        this.transactionDetailService = transactionDetailService;
    }

    @PostMapping(value = "/orders")
    public ResponseEntity<BaseResponse<String>> createOrder(@RequestBody OrderForm form, HttpServletRequest request) {
        try {
            // get username from http request
            return ResponseEntity.ok(orderService.createOrder(form,request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse<>(null, e.getMessage()));
        }
    }

    @GetMapping(value = "/getOrderDetail/{id}")
    public BaseResponse<TransactionDetail> getTransactionDetail(@PathVariable int id) {
        BaseResponse<TransactionDetail> response = transactionDetailService.findById(id);
        return ResponseEntity.ok(response).getBody();
    }
}

