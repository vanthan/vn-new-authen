package com.vanthan.vn.service.impl;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.ProductForm;
import com.vanthan.vn.dto.RegisterResult;
import com.vanthan.vn.model.Product;
import com.vanthan.vn.repository.ProductRepository;
import com.vanthan.vn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public BaseResponse<RegisterResult> createProduct(ProductForm form) {
        BaseResponse response = new BaseResponse<RegisterResult>();
        // check product based on SKU
        // list
        List<Product> product = new ArrayList<>();
        product = productRepository.findBySku(form.getSku());
        // if existed --> update quantity
        if (product != null & !product.isEmpty()) {
            response.setCode("11");
            response.setMessage("Please add a number of quantity");
            return response;
        }
        // create new product
        Product product1 = new Product();
        product1.setSku(form.getSku());
        product1.setName(form.getName());
        product1.setQuantity(form.getQuantity());

        // save to db
        productRepository.save(product1);
        response.setCode("00");
        response.setMessage("Added a new product! :)");
        return response;
    }

    @Override
    public List<Product> getProducts(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        return productRepository.findAll(paging).toList();
    }

}
