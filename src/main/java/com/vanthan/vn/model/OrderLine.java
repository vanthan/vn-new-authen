package com.vanthan.vn.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "...")
@Data
@AllArgsConstructor
public class OrderLine {
    @ManyToOne(targetEntity = Product.class)
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(targetEntity = Order.class)
    private Order order;
}
