package com.vanthan.vn.dto;
import lombok.Data;

@Data
public class OrderDetailResult {
    private int productId;
    private String productName;
    private int quantity;
    private int price;
    private int total;

}