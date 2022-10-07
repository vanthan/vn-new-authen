package com.vanthan.vn.controller;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.ProductForm;
import com.vanthan.vn.dto.RegisterResult;
import com.vanthan.vn.model.Paging;
import com.vanthan.vn.model.Product;
import com.vanthan.vn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = "/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "products")
    public ResponseEntity<BaseResponse<Product>> createProduct(@RequestBody ProductForm body){
        return ResponseEntity.ok(productService.createProduct(body));
    }

    @PostMapping(value = "getProducts")
    public Page<Product> getProducts(@RequestBody Paging body) {
        Paging paging =new Paging();
        paging.setPageNum(body.getPageNum());
        paging.setTotalNum(body.getTotalNum());
        PageRequest pageRequest = PageRequest.of(body.getPageNum(), body.getTotalNum());
        return productService.getProducts(pageRequest);
    }

    @PutMapping(value = "products/{id}")
    public BaseResponse<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
        return productService.updateById(product);
    }

    @DeleteMapping(value ="products/{id}")
    public BaseResponse<String> deleteProduct(@PathVariable int id) {
        return productService.deleteById(id);
    }
}
