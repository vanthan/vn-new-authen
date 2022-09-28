package com.vanthan.vn.service;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.RegisterDTO;
import com.vanthan.vn.dto.RegisterResponse;
import com.vanthan.vn.model.User;

public interface ImpAuthen {

    BaseResponse<RegisterResponse> register(RegisterDTO body);
}
