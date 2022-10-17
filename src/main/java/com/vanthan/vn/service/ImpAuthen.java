package com.vanthan.vn.service;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.RegisterForm;
import com.vanthan.vn.dto.RegisterResult;


public interface ImpAuthen {

    BaseResponse<RegisterResult> register(RegisterForm body);

}
