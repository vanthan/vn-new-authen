package com.vanthan.vn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "vn_order_line")
@AllArgsConstructor
@NoArgsConstructor
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(targetEntity = Product.class)
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(targetEntity = Order.class)
    private Order order;


}
