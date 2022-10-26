//package com.vanthan.vn.model;
//
//import lombok.Data;
//
//import javax.persistence.*;
//import java.sql.Timestamp;
//import java.util.List;
//
//@Data
//@Entity
//@Table(name="vn_order_result")
//public class OrderResult {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private int id;
//    private Timestamp createdAt;
//    @OneToMany
//    private List<OrderItem> items;
//    private int totalItems;
//    private int totalCost;
//
//
//}
