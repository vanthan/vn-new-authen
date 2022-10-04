package com.vanthan.vn.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "vn_customer")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String userName;
    private String email;
    private String age;
}
