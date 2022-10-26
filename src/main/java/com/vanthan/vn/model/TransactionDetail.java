package com.vanthan.vn.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="vn_transaction_detail")
public class TransactionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int orderId;
    private String email;
    private String fullName;
    private int totalItem;
    private int totalCost;
    private String status;
    private String paymentMethod;


}
