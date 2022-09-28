package com.vanthan.vn.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "vn_user")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private String email;
    private String password;
}
