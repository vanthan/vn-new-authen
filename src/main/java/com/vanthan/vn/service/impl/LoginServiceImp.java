package com.vanthan.vn.service.impl;

import com.vanthan.vn.dto.*;
import com.vanthan.vn.jwt.JwtUtils;
import com.vanthan.vn.model.entity.User;
import com.vanthan.vn.model.entity.UserToken;
import com.vanthan.vn.repository.LoginRespository;
import com.vanthan.vn.repository.UserTokenRespository;
import com.vanthan.vn.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImp implements LoginService {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private LoginRespository loginRepository;
    @Autowired
    private UserTokenRespository userTokenRespository;

    @Override
    public BaseResponse<LoginResponse> checkLogin(UserForm body) throws Exception {
        User user = loginRepository.findByEmailAndPassword(body.getEmail(), body.getPassword());
        if (user == null) {
            throw new Exception("Email hoac mat khau khong ton tai");
        }

        RegisterResult registerResult = new RegisterResult();

        UserResult userInfo = new UserResult();
        userInfo.setUserName(user.getUsername());
        userInfo.setUserId(user.getId());
        userInfo.setEmail(user.getEmail());
        String tokenValue = jwtUtils.generateJwtToken(userInfo);
        registerResult.setToken(tokenValue);

        //update token db
        User user1 = loginRepository.findById(user.getId())
                .orElseThrow(() -> new Exception("not_found"));
        UserToken token = userTokenRespository.findUserTokenByUserId(user.getId());
//        token.setToken(registerResult.getToken());
        token.setToken(tokenValue);
        token.setUserId(user1.getId());
        userTokenRespository.save(token);

//        UserToken token = new UserToken();
//        token.setToken(registerResult.getToken());
//        token.setUserId(user.getId());
//        userTokenRespository.save(token);

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
