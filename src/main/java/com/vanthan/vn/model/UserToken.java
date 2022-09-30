package com.vanthan.vn.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "vn_token")
@Data
public class UserToken {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String token;
}
