package com.vanthan.vn.dto;

import com.vanthan.vn.model.Order;
import com.vanthan.vn.model.OrderLine;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderResult {
    private int id;
    private List<OrderLineResult> orderLines;

    public static OrderResult fromOrder(Order order) {
        OrderResult result = new OrderResult();
        List<OrderLineResult> orderLines = new ArrayList<>();
        for (OrderLine orderLine : order.getOrderLines()) {
            OrderLineResult orderLineResult = new OrderLineResult();
            orderLineResult.setProductId(orderLine.getProduct().getId());
            orderLineResult.setProductName(orderLine.getProduct().getName());
            orderLineResult.setQuantity(orderLine.getQuantity());
            orderLines.add(orderLineResult);
        }
        result.setId(order.getId());
        result.setOrderLines(orderLines);
        return result;
    }
}
