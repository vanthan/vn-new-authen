package com.vanthan.vn.controller;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.ProductForm;
import com.vanthan.vn.dto.RegisterResult;
import com.vanthan.vn.model.Customer;
import com.vanthan.vn.model.Paging;
import com.vanthan.vn.model.Product;
import com.vanthan.vn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "products")
    public ResponseEntity<BaseResponse<Product>> createProduct(@RequestBody ProductForm body, HttpServletRequest request) {
        return ResponseEntity.ok(productService.createProduct(body, request));
    }

    @PostMapping(value = "getProducts")
    public BaseResponse<Page<Product>> getProducts(@RequestBody Paging body) {
        Paging paging = new Paging();
        paging.setPageNum(body.getPageNum());
        paging.setTotalNum(body.getTotalNum());
        PageRequest pageRequest = PageRequest.of(body.getPageNum(), body.getTotalNum());
        return productService.getProducts(pageRequest);
    }

    @PutMapping(value = "products/{id}")
    public BaseResponse<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
        return productService.updateById(product);
    }

    @DeleteMapping(value = "products/{id}")
    public BaseResponse<String> deleteProduct(@PathVariable int id) {
        return productService.deleteById(id);
    }

    @PostMapping("search")
    public ResponseEntity<BaseResponse<Page<Product>>> searchProduct(@RequestParam(value = "name") String name, @RequestBody Paging paging) {
        Paging paging1 = new Paging();
        paging1.setPageNum(paging.getPageNum());
        paging1.setTotalNum(paging.getTotalNum());
        PageRequest pageRequest = PageRequest.of(paging.getPageNum(), paging.getTotalNum());
        BaseResponse<Page<Product>> rs = productService.searchProductByName(name, pageRequest);

        return ResponseEntity.ok(rs);
    }

    @GetMapping("/productDetail/{id}")
    public ResponseEntity<BaseResponse<Optional<Product>>> findById(@PathVariable Integer id) {
        BaseResponse<Optional<Product>> response = productService.findById(id);
        return ResponseEntity.ok(response);
    }
}
