package com.vanthan.vn.service;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.LoginResponse;
import com.vanthan.vn.dto.UserForm;

public interface LoginService {
    BaseResponse<LoginResponse> checkLogin(UserForm body) throws Exception;
}
