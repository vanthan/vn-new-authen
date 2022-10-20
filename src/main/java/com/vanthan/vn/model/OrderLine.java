package com.vanthan.vn.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "vn_order_line")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int productId;
    private int quantity;
}
