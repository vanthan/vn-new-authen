package com.vanthan.vn.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class OrderResult {
    private int id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Timestamp createdAt;
    private UserResult userResult;
    private List<OrderItemResult> items;
    private int totalItems;
    private int totalCost;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PaymentDetailsResult paymentDetails;
}
