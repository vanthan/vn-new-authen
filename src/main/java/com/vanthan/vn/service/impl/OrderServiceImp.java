package com.vanthan.vn.service.impl;

import com.vanthan.vn.dto.*;
import com.vanthan.vn.jwt.AuthTokenFilter;
import com.vanthan.vn.jwt.JwtUtils;
import com.vanthan.vn.model.Order;
import com.vanthan.vn.model.OrderDetail;
import com.vanthan.vn.model.Product;
import com.vanthan.vn.model.TransactionDetail;
import com.vanthan.vn.repository.OrderDetailRepository;
import com.vanthan.vn.repository.OrderRepository;
import com.vanthan.vn.repository.ProductRepository;
import com.vanthan.vn.repository.TransactionDetailRepository;
import com.vanthan.vn.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderServiceImp implements OrderService {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final TransactionDetailRepository transactionDetailRepository;
    private final JwtUtils jwtUtils;

    private final AuthTokenFilter authTokenFilter;


    @Autowired
    public OrderServiceImp(OrderDetailRepository orderDetailRepository, OrderRepository orderRepository, ProductRepository productRepository, TransactionDetailRepository transactionDetailRepository, JwtUtils jwtUtils, AuthTokenFilter authTokenFilter) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.transactionDetailRepository = transactionDetailRepository;
        this.jwtUtils = jwtUtils;
        this.authTokenFilter = authTokenFilter;
    }

    @Override
    public BaseResponse<String> createOrder(OrderForm form, HttpServletRequest request) {
        BaseResponse<String> response = new BaseResponse<>();
        List<OrderLineForm> orderLines = form.getOrderLines();
        Order order = new Order();


        // get info from token: email + full name
        String token = authTokenFilter.parseJwt(request);

        Map<String,Object> userInfo = jwtUtils.getClaimFromToken(token, claims -> {return claims;});
        int userid = Integer.parseInt(userInfo.get("id").toString());
        String email = userInfo.get("email").toString();
        String username = userInfo.get("username").toString();

        // save order
        order.setDeliveryCode("mamama");
        order.setCustomerId(userid);
        orderRepository.save(order);

        // save transaction detail
        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setOrderId(order.getId());
        transactionDetail.setPaymentMethod("cash");
        transactionDetail.setStatus("done");
        transactionDetail.setTotal(0);

        // find product
        for (OrderLineForm orderLine : orderLines) {
            Optional<Product> maybeProduct = productRepository.findById( orderLine.getProductId());
            if (!maybeProduct.isPresent()) {
                response.setCode("001");
                response.setMessage("Product not found: " +  orderLine.getProductId());
                return response;
            }
            // get product
            Product product = maybeProduct.get();
            // update quantity in db


            // save update product details
            productRepository.save(product);
            // save order detail
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(order.getId());
            orderDetail.setProductId( orderLine.getProductId());
            orderDetail.setQuantity(orderLine.getQuantity());
            orderDetailRepository.save(orderDetail);

            //set transaction total
            transactionDetail.setTotal(transactionDetail.getTotal() + (product.getPrice() * orderLine.getQuantity()));

            }
        // transaction
        transactionDetailRepository.save(transactionDetail);

        response.setCode("00");
        response.setMessage("Created an order");
        response.setBody(orderLines.toString());
        return response;
    }
}