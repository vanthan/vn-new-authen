package com.vanthan.vn.service.impl;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.LoginResponse;
import com.vanthan.vn.dto.UserDTO;
import com.vanthan.vn.model.User;
import com.vanthan.vn.respository.LoginRespository;
import com.vanthan.vn.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRespository loginRespository;

    @Override
    public BaseResponse<LoginResponse> checkLogin(UserDTO body) throws Exception {
        System.out.println(body.getEmail());
        System.out.println(body.getPassword());
        User user = loginRespository.findByEmailAndPassword(body.getEmail(), body.getPassword());
        System.out.println(user);
        if (user == null) {
            throw new Exception("Email hoac mat khau khong ton tai");
        }
        LoginResponse logRes = new LoginResponse();
        logRes.setEmail(user.getEmail());
        logRes.setToken("TOKEN");
        BaseResponse response = new BaseResponse<LoginResponse>();
        response.setCode("1234");
        response.setCode("Success");
        response.setBody(logRes);

        return response;
    }


}
