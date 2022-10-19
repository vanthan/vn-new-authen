package com.vanthan.vn.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderForm {
    private int productId;
    private int quantity;
    private int userId;
}
