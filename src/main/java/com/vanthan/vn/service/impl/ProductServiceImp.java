package com.vanthan.vn.service.impl;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.ProductForm;
import com.vanthan.vn.dto.RegisterResult;
import com.vanthan.vn.dto.UserInfo;
import com.vanthan.vn.jwt.AuthEntryPointJwt;
import com.vanthan.vn.jwt.JwtUtils;
import com.vanthan.vn.model.Paging;
import com.vanthan.vn.model.Product;
import com.vanthan.vn.repository.ProductRepository;
import com.vanthan.vn.repository.UserTokenRespository;
import com.vanthan.vn.service.ProductService;
import io.jsonwebtoken.Claims;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserTokenRespository tokenRespository;

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AuthEntryPointJwt authEntryPointJwt;

    @Override
    public BaseResponse<Product> createProduct(ProductForm form, HttpServletRequest request) {
        BaseResponse response = new BaseResponse<RegisterResult>();
        // check product based on SKU
        // list
        List<Product> product = new ArrayList<>();
        product = productRepository.findBySku(form.getSku());
        // if existed --> update quantity
        if (product != null & !product.isEmpty()) {
            response.setCode("11");
            response.setMessage("SKU already existed!");
            return response;
        }
        // get username from http request
        String token = authEntryPointJwt.parseJwt(request);

        Map<String,Object> userInfo = jwtUtils.getClaimFromToken(token, claims -> {return claims;});
        String username = userInfo.get("userName").toString();
        // create new product
        Product product1 = new Product();
        product1.setSku(form.getSku());
        product1.setName(form.getName());
        product1.setQuantity(form.getQuantity());
        product1.setCreatedBy(username);

        log.info("User info {}", userInfo);

        // save to db
        productRepository.save(product1);
        response.setCode("00");
        response.setMessage("Added a new product! :)");
        response.setBody(product1);
        return response;
    }

    @Override
    public BaseResponse<Page<Product>> getProducts(PageRequest request) {
        BaseResponse response = new BaseResponse();
        response.setBody(productRepository.findAll(request));
        return response;
    }

    @Override
    public BaseResponse<Product> updateById(Product product) {
        BaseResponse<Product> response = new BaseResponse<>();
        // check product id
        Optional<Product> maybeProduct = productRepository.findById(product.getId());
        System.out.println(maybeProduct);
        Product updateProduct = maybeProduct.orElseThrow(() -> new ResourceNotFoundException("Product does not exist with id: "));

        // update
        updateProduct.setSku(product.getSku());
        updateProduct.setName(product.getName());
        updateProduct.setQuantity(product.getQuantity());

        // save to db
        productRepository.save(updateProduct);
        response.setMessage("Updated!");
        response.setBody(updateProduct);
        return response;
    }

    @Override
    public BaseResponse<String> deleteById(int id) {
        BaseResponse<String> response = new BaseResponse<>();
        productRepository.deleteById(id);
        response.setMessage("Deleted successfully!");
        return response;
    }
}
