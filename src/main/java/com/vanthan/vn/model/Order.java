package com.vanthan.vn.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vn_order")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(targetEntity = OrderLine.class)
    private List<OrderLine> orderLines = new ArrayList<>();

    public void addOrderLine(Product product, int quantity) {
        this.orderLines.add(new OrderLine(product, quantity));
    }
}
