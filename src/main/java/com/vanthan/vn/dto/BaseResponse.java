package com.vanthan.vn.dto;

import lombok.Data;

@Data
public class BaseResponse<T> {
    private String code;
    private String message;
    private T body;
}
