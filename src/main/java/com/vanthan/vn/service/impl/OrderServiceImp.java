package com.vanthan.vn.service.impl;

import com.vanthan.vn.dto.OrderForm;
import com.vanthan.vn.dto.OrderLineForm;
import com.vanthan.vn.dto.OrderResult;
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
    public OrderResult createOrder(OrderForm form) {
        final List<OrderLineForm> orderLines = form.getOrderLines();
        final Order order = new Order();
        for (OrderLineForm orderLine : orderLines) {
            int productId = orderLine.getProductId();
            int quantity = orderLine.getQuantity();
            Optional<Product> maybeProduct = productRepository.findById(productId);
            if (!maybeProduct.isPresent()) {
                throw new IllegalArgumentException("Product not found: " + productId);
            }
            Product product = maybeProduct.get();
            order.addOrderLine(product, quantity);
            product.setQuantity(product.getQuantity() - quantity);
            productRepository.save(product);
        }
        orderRepository.save(order);
        return OrderResult.fromOrder(order);
    }
}