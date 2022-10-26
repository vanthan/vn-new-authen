package com.vanthan.vn.service.impl;

import com.vanthan.vn.dto.*;
import com.vanthan.vn.jwt.AuthTokenFilter;
import com.vanthan.vn.jwt.JwtUtils;
import com.vanthan.vn.model.Order;
import com.vanthan.vn.model.OrderDetail;
import com.vanthan.vn.model.TransactionDetail;
import com.vanthan.vn.model.entity.Product;
import com.vanthan.vn.repository.OrderDetailRepository;
import com.vanthan.vn.repository.OrderRepository;
import com.vanthan.vn.repository.ProductRepository;
import com.vanthan.vn.repository.TransactionDetailRepository;
import com.vanthan.vn.service.OrderService;
import com.vanthan.vn.util.CommonUtil;
import com.vanthan.vn.util.Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
@Log4j2
public class OrderServiceImp implements OrderService {

    @Value("${base.url.authen}")
    private String baseUrl;
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
    public BaseResponse<OrderResult> createOrder(OrderForm form, HttpServletRequest request) {
        BaseResponse<OrderResult> response = new BaseResponse<>();
        EmailDTO emailDTO = new EmailDTO();
        // Map props => set props email
        Map<String, Object> props = new HashMap<String, Object>();

        List<OrderLineForm> orderLines = form.getOrderLines();
        Order order = new Order();
        List<OrderDetailResult> orderDetailResultList = new ArrayList<>();

        // get info from token: email + full name
        String token = authTokenFilter.parseJwt(request);

        Map<String,Object> userInfo = jwtUtils.getClaimFromToken(token, claims -> {return claims;});
        int userid = Integer.parseInt(userInfo.get("id").toString());
        String email = userInfo.get("email").toString();
        String username = userInfo.get("username").toString();

        // SET EMAILĐTO
        emailDTO.setTemplate("mail-template");
        emailDTO.setSubject("Subject_TEST");
        emailDTO.setRecipient(email);



        // save order
        order.setDeliveryCode(order.generateRandomCode());
        order.setCustomerId(userid);
        orderRepository.save(order);

        // save transaction detail
        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setOrderId(order.getId());

        transactionDetail.setPaymentMethod("cash");
        transactionDetail.setStatus("done");
        transactionDetail.setTotalCost(0);



        // find product
        for (OrderLineForm orderLine : orderLines) {
            Optional<Product> maybeProduct = productRepository.findById(orderLine.getProductId());
            if (!maybeProduct.isPresent()) {
                response.setCode("001");
                response.setMessage("Product not found: " +  orderLine.getProductId());
                return response;
            }
            // get product
            Product product = maybeProduct.get();
            // update quantity in db

            if (product.getQuantity() < orderLine.getQuantity()){
                throw new IllegalArgumentException("Product is out of stock: " + orderLine.getProductId());
            }
            product.setQuantity(product.getQuantity() - orderLine.getQuantity());


            // save update product details
            productRepository.save(product);
            // save order detail - save to db
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(order.getId());
            orderDetail.setProductId(orderLine.getProductId());
            orderDetail.setQuantity(orderLine.getQuantity());
            orderDetailRepository.save(orderDetail);
            // ket qua cua service - 1 loai DTO o dang cu the
            OrderDetailResult orderDetailResult = new OrderDetailResult();
            orderDetailResult.setProductId(product.getId());
            orderDetailResult.setProductName(product.getName());
            orderDetailResult.setQuantity(orderLine.getQuantity());
            orderDetailResult.setPrice(product.getPrice());
            orderDetailResult.setTotal(orderLine.getQuantity() * product.getPrice());

            orderDetailResultList.add(orderDetailResult);

            //set transaction total
            transactionDetail.setTotalItem(orderLine.getQuantity());
            transactionDetail.setTotalCost(transactionDetail.getTotalCost() + (product.getPrice() * orderLine.getQuantity()));
        }
        // save transaction to db
        transactionDetailRepository.save(transactionDetail);

        response.setCode("00");
        response.setMessage("Created an order");

        // return response - OR la 1 object
        OrderResult result = new OrderResult();
        result.setOrderDetailList(orderDetailResultList);
        response.setBody(result);

        // Call API Send Mail
        String url = baseUrl + "/sendMail";
        props.put("fullName", username);
        props.put("product", orderDetailResultList);
        props.put("paymentMethod", transactionDetail.getPaymentMethod());
        props.put("price", transactionDetail.getTotalCost());
        props.put("total", transactionDetail.getTotalCost());
        props.put("status", transactionDetail.getStatus());
        emailDTO.setProps(props);

        log.info("Resquest Body {}", CommonUtil.convertFromObject(emailDTO));

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<EmailDTO> requestSaveProduct = new HttpEntity<>(emailDTO, headers);
        restTemplate.exchange(url, HttpMethod.POST, requestSaveProduct,new ParameterizedTypeReference<BaseResponse<Object>>() {} );




        return response;
    }

    @Override
    public BaseResponse<OrderResult> getOrder(int orderId) {
        BaseResponse<OrderResult> response = new BaseResponse<>();

        //get order detail
        List<OrderDetailResult> orderDetailResultList = new ArrayList<>();
        //orderDetailResultList = orderDetailRepository.findById(orderId);
        //get transaction detail by order id
        List<TransactionDetail> transactionDetailList = new ArrayList<>();
        transactionDetailList = transactionDetailRepository.findByOrderId(orderId);
        // if existed --> update quantity
        if (transactionDetailList == null || transactionDetailList.isEmpty()) {
            response.setCode("11");
            response.setMessage("Not found: " + orderId);
            return response;
        }

        OrderResult orderResult = new OrderResult();
        orderResult.setTransactionDetailList(transactionDetailList);
        response.setBody(orderResult);
        return response;
    }
}