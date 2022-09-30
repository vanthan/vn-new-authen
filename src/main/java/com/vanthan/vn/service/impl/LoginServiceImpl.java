package com.vanthan.vn.service.impl;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.LoginResponse;
import com.vanthan.vn.dto.UserDTO;
import com.vanthan.vn.model.User;
import com.vanthan.vn.model.UserToken;
import com.vanthan.vn.repository.TokenRespository;
import com.vanthan.vn.repository.LoginRespository;
import com.vanthan.vn.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRespository loginRepository;

    @Autowired
    private TokenRespository tokenRespository;

    @Override
    public BaseResponse<LoginResponse> checkLogin(UserDTO body) throws Exception {
        User user = loginRepository.findByEmailAndPassword(body.getEmail(), body.getPassword());
        if (user == null) {
            throw new Exception("Email hoac mat khau khong ton tai");
        }

        UserToken token = new UserToken();
        token.setToken("TOKEN2");
        token.setUser_id(user.getId());
        tokenRespository.save(token);

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
