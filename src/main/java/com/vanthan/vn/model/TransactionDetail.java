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
    private String email;
//    private String fullName;
    private String productName;
    private int quantity;
    private int total;
    private String status;
    private String paymentMethod;

}
