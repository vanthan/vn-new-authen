package com.vanthan.vn.dto;

import com.vanthan.vn.model.Order;
import com.vanthan.vn.model.OrderDetail;
import com.vanthan.vn.model.TransactionDetail;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderResult {
    private List<OrderDetailResult> orderDetailList;
    private List<TransactionDetail> transactionDetailList;
}
