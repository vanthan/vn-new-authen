package com.vanthan.vn.dto;

import lombok.Data;

@Data
public class OrderResult {
    private int id;
    private String productName;
    private int quantity;
    private double price;
    private String orderedBy;
}
