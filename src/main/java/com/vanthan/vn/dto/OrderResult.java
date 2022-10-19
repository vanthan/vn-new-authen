package com.vanthan.vn.dto;

import lombok.Data;

@Data
public class OrderResult {
    private int id;
    private int productId;
    private int quantity;
    private String orderedBy;
    private double total;
}
