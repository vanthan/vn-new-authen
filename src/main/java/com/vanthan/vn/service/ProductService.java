package com.vanthan.vn.service;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.ProductForm;
import com.vanthan.vn.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface ProductService {
    BaseResponse<Product> createProduct(ProductForm form, HttpServletRequest request);

    BaseResponse<Page<Product>> getProducts(PageRequest request);

    BaseResponse<Product> updateById(Product product);

    BaseResponse<String> deleteById(int id);

    BaseResponse<Page<Product>> searchProductByName(String name, PageRequest request);

    BaseResponse<Optional<Product>> findById(Integer id);
}
