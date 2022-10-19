package com.vanthan.vn.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "vn_order_line")
public class OrderLine {
    private int id;
    private int productId;
    private int quantity;
}
