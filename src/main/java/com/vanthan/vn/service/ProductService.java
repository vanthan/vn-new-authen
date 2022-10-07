package com.vanthan.vn.service;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.ProductForm;
import com.vanthan.vn.dto.RegisterResult;
import com.vanthan.vn.model.Paging;
import com.vanthan.vn.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ProductService {
    BaseResponse<Product> createProduct(ProductForm form);
    Page<Product> getProducts(PageRequest request);

    BaseResponse<Product> updateById(Product product);

    BaseResponse<String> deleteById(int id);
}
