package com.vanthan.vn.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Table(name = "vn_product")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String sku;
    private String image;
    private String name;
    private int price;
    private int quantity;
    private String createdBy;

    public Product() {

    }
}
