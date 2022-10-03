package com.vanthan.vn.service;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.LoginResponse;
import com.vanthan.vn.dto.UserDTO;

public interface LoginService {
    BaseResponse<LoginResponse> checkLogin(UserDTO body) throws Exception;
}
