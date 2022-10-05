package com.vanthan.vn.service;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.ProductForm;
import com.vanthan.vn.dto.RegisterResult;
import com.vanthan.vn.model.Product;
import org.springframework.data.domain.Page;


import java.util.List;

import java.util.List;

public interface ProductService {
    BaseResponse<RegisterResult> createProduct(ProductForm form);

<<<<<<< Updated upstream
    List<Product> getProducts(int pageNo, int pageSize);


=======
    List<Product> getProductWithPagination(int pageNo, int pageSize);
>>>>>>> Stashed changes
}
