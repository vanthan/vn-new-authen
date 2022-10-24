package com.vanthan.vn.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductForm {
    private String sku;
    private String name;
    private int price;
    private String image;
    private int quantity;
}
