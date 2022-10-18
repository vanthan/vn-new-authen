package com.vanthan.vn.service.impl;

import com.vanthan.vn.dto.*;
import com.vanthan.vn.model.Order;
import com.vanthan.vn.model.Product;
import com.vanthan.vn.repository.OrderRepository;
import com.vanthan.vn.repository.ProductRepository;
import com.vanthan.vn.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImp implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderServiceImp(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public BaseResponse<OrderResult> createOrder(OrderForm form) {
        BaseResponse response = new BaseResponse<OrderResult>();
        // find product
        Optional<Product> maybeProduct = productRepository.findById(form.getProductId());
        if (!maybeProduct.isPresent()) {
            response.setCode("001");
            throw new IllegalArgumentException("Product not found");
        }
        // get product
        Product product = maybeProduct.get();
        //create order
        final Order order = new Order(form.getUserId(), form.getProductId(), form.getQuantity());
        // update quantity in db
        product.setQuantity(product.getQuantity() - form.getQuantity());
        // save product details
        productRepository.save(product);

        // save order
        orderRepository.save(order);
        response.setCode("00");
        response.setMessage("Created an order");
        response.setBody(order);
        return response;
    }
}