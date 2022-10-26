package com.vanthan.vn.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class UserResult {
    private Integer userId;
    private String email;
    private String userName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String fullName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;

    public UserResult(){}

    public UserResult(Integer userId, String email, String userName) {
        this.userId = userId;
        this.email = email;
        this.userName = userName;
    }
}
