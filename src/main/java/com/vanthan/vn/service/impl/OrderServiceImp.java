package com.vanthan.vn.service.impl;

import com.vanthan.vn.dto.*;
import com.vanthan.vn.jwt.AuthTokenFilter;
import com.vanthan.vn.jwt.JwtUtils;
import com.vanthan.vn.model.OrderLine;
import com.vanthan.vn.model.Product;
import com.vanthan.vn.model.TransactionDetail;
import com.vanthan.vn.repository.OrderLineRepository;
import com.vanthan.vn.repository.ProductRepository;
import com.vanthan.vn.repository.TransactionDetailRepository;
import com.vanthan.vn.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderServiceImp implements OrderService {
    private final OrderLineRepository orderRepository;
    private final ProductRepository productRepository;
    private final TransactionDetailRepository transactionDetailRepository;
    private final JwtUtils jwtUtils;

    private final AuthTokenFilter authTokenFilter;


    @Autowired
    public OrderServiceImp(OrderLineRepository orderRepository, ProductRepository productRepository, TransactionDetailRepository transactionDetailRepository, JwtUtils jwtUtils, AuthTokenFilter authTokenFilter) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.transactionDetailRepository = transactionDetailRepository;
        this.jwtUtils = jwtUtils;
        this.authTokenFilter = authTokenFilter;
    }

    @Override
    public BaseResponse<TransactionDetail> createOrder(OrderForm form, HttpServletRequest request) {
        BaseResponse<TransactionDetail> response = new BaseResponse<>();
        // find product
        Optional<Product> maybeProduct = productRepository.findById(form.getProductId());
        if (!maybeProduct.isPresent()) {
            response.setCode("001");
            response.setMessage("Product not found: " + form.getProductId());
            return response;
        }
        // get product
        Product product = maybeProduct.get();
        //create order
        OrderLine orderLine = new OrderLine();
        // update quantity in db
        product.setQuantity(product.getQuantity() - form.getQuantity());
        // save update product details
        productRepository.save(product);
        // save order
        orderRepository.save(orderLine);
        // get info from token: email + full name
        String token = authTokenFilter.parseJwt(request);

        Map<String,Object> userInfo = jwtUtils.getClaimFromToken(token, claims -> {return claims;});
        String email = userInfo.get("email").toString();

//        Map<String,Object> userInfo1 = jwtUtils.getClaimFromToken(token, claims -> {return claims;});
//        String fullName = userInfo1.get("fullName").toString();
//            form.setUserId(userId);
        // create order transaction detail
        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setId(orderLine.getId());
        //transactionDetail.setFullName(fullName);
        transactionDetail.setEmail(email);
        transactionDetail.setProductName(product.getName());
        transactionDetail.setQuantity(orderLine.getQuantity());
        transactionDetail.setTotal(product.getPrice()*orderLine.getQuantity());
        transactionDetail.setStatus("pending");
        transactionDetail.setPaymentMethod("cash");

        // transaction detail
        transactionDetailRepository.save(transactionDetail);

        response.setCode("00");
        response.setMessage("Created an order");
        response.setBody(transactionDetail);
        return response;
    }
}