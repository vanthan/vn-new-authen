package com.vanthan.vn.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class OrderResult {
    private int id;
    private Timestamp createdAt;
    private UserResult userResult;
    private List<OrderItemResult> items;
    private int totalItems;
    private int totalCost;
    private PaymentDetailsResult paymentDetails;
}
