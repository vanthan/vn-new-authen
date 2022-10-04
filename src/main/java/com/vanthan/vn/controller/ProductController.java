package com.vanthan.vn.controller;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.ProductForm;
import com.vanthan.vn.dto.RegisterResult;
import com.vanthan.vn.repository.ProductRepository;
import com.vanthan.vn.service.ImpAuthen;
import com.vanthan.vn.service.ProductService;
import com.vanthan.vn.service.impl.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "products")
    public ResponseEntity<BaseResponse<RegisterResult>> createProduct(@RequestBody ProductForm body){
        return ResponseEntity.ok(productService.createProduct(body));
    }

}
