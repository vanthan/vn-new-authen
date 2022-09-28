package com.vanthan.vn.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String userId;
}
