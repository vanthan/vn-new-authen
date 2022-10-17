package com.vanthan.vn.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "vn_customer")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "email")
    private String email;
    @Column(name = "age")
    private String age;
}
