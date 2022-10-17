package com.vanthan.vn.dto;

import lombok.Data;

@Data
public class OrderLineResult {
    private int productId;
    private String productName;
    private int quantity;
}
