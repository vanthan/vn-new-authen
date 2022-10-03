package com.vanthan.vn.service;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.ProductForm;
import com.vanthan.vn.model.Product;

public interface ProductService {
    BaseResponse<String> createProduct(ProductForm form);
}