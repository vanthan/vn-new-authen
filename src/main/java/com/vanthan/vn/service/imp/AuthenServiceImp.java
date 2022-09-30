package com.vanthan.vn.service.imp;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.RegisterForm;
import com.vanthan.vn.dto.RegisterResult;
import com.vanthan.vn.dto.UserInfo;
import com.vanthan.vn.jwt.JwtUtils;
import com.vanthan.vn.model.User;
import com.vanthan.vn.model.UserToken;
import com.vanthan.vn.repository.UserRepository;
import com.vanthan.vn.repository.UserTokenRespository;
import com.vanthan.vn.service.ImpAuthen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenServiceImp implements ImpAuthen {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTokenRespository tokenRespository;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public BaseResponse<RegisterResult> register(RegisterForm body) {
        User user = new User();
        BaseResponse response = new BaseResponse<RegisterResult>();
        //check user by user name
        user = userRepository.findByUsername(body.getUsername());
        if (user != null){
            response.setCode("001");
            response.setMessage("User already existed");
            return response;
        }

        //crate new user
        user = new User();
        user.setFullName(body.getFullName());
        user.setEmail(body.getEmail());
        user.setUsername(body.getUsername());
        user.setPassword(body.getPassword());
        userRepository.save(user);
        // result
        response.setCode("00");
        response.setMessage("Successfully created a new user!");

        //create token, add token
        RegisterResult registerResult = new RegisterResult();
        // result token
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(body.getUsername());
        userInfo.setUserId(user.getId());
        userInfo.setEmail(user.getEmail());
        registerResult.setToken(jwtUtils.generateJwtToken(userInfo));
        UserToken userToken = new UserToken();
        userToken.setToken(registerResult.getToken());
        userToken.setUser_id(user.getId());
        tokenRespository.save(userToken);

        response.setBody(registerResult);

        return response;
    }
}
