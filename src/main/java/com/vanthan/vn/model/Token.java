package com.vanthan.vn.model;

import lombok.Data;

import javax.persistence.*;

//@Entity
//@Table(name = "vn_user")
//@Data

public class Token {
//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private int user_id;
    private int user_token_id;
}
