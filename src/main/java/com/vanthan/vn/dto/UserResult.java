package com.vanthan.vn.dto;

import lombok.Data;

@Data
public class UserResult {
    private Integer userId;
    private String email;
    private String userName;
    private String fullName;
    private String password;
}
