package com.vanthan.vn.service.imp;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.ProductForm;
import com.vanthan.vn.model.Product;
import com.vanthan.vn.repository.ProductRepository;
import com.vanthan.vn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public BaseResponse<String> createProduct(ProductForm form) {
        BaseResponse response = new BaseResponse<>();
        // check product based on SKU
        List<Product> product = new ArrayList<>();
        product = productRepository.findBySKU(form.getSKU());
        // if existed --> update quantity
        if (product != null) {
            response.setCode("11");
            response.setMessage("Please add a number of quantity");
        }
        // create new product
        Product product1 = new Product();
        product1.setSKU(form.getSKU());
        product1.setName(form.getName());
        product1.setQuantity(form.getQuantity());

        // save to db
        productRepository.save(product1);
        response.setCode("00");
        response.setMessage("Added a new product! :)");
        return response;
    }
}
