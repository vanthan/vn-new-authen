package com.vanthan.vn.controller;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.ProductForm;
import com.vanthan.vn.dto.RegisterResult;
import com.vanthan.vn.model.Product;
import com.vanthan.vn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "products")
    public ResponseEntity<BaseResponse<RegisterResult>> createProduct(@RequestBody ProductForm body){
        return ResponseEntity.ok(productService.createProduct(body));
    }

    @GetMapping(value = "getProducts")
    public List<Product> getProducts(int pageNo, int pageSize) {

        return productService.getProducts(pageNo, pageSize);
    }


}
