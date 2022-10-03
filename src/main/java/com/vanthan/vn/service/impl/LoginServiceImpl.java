package com.vanthan.vn.service.impl;

import com.vanthan.vn.dto.*;
import com.vanthan.vn.jwt.JwtUtils;
import com.vanthan.vn.model.User;
import com.vanthan.vn.model.UserToken;
import com.vanthan.vn.repository.LoginRespository;
import com.vanthan.vn.repository.UserTokenRespository;
import com.vanthan.vn.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRespository loginRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UserTokenRespository userTokenRespository;

    @Override
    public BaseResponse<LoginResponse> checkLogin(UserDTO body) throws Exception {
        User user = loginRepository.findByEmailAndPassword(body.getEmail(), body.getPassword());
        if (user == null) {
            throw new Exception("Email hoac mat khau khong ton tai");
        }

        RegisterResult registerResult = new RegisterResult();

        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(body.getUserName());
        userInfo.setUserId(user.getId());
        userInfo.setEmail(user.getEmail());
        registerResult.setToken(jwtUtils.generateJwtToken(userInfo));
        UserToken token = new UserToken();
        token.setToken(registerResult.getToken());
        token.setUser_id(user.getId());
        userTokenRespository.save(token);

        LoginResponse logRes = new LoginResponse();
        logRes.setEmail(user.getEmail());
        logRes.setToken(registerResult.getToken());
        BaseResponse response = new BaseResponse<LoginResponse>();
        response.setCode("00");
        response.setMessage("Success");
        response.setBody(logRes);

        return response;
    }


}
