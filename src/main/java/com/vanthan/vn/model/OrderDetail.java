package com.vanthan.vn.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "vn_order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;
    private int orderId;
    private int productId;
    private int quantity;
    private int price;
}
