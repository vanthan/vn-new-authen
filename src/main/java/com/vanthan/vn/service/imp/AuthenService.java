package com.vanthan.vn.service.imp;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.RegisterDTO;
import com.vanthan.vn.dto.RegisterResponse;
import com.vanthan.vn.model.User;
import com.vanthan.vn.repository.UserRepository;
import com.vanthan.vn.service.ImpAuthen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

@Service
public class AuthenService implements ImpAuthen {

    @Autowired
    private UserRepository userRepository;

    @Override
    public BaseResponse<RegisterResponse> register(RegisterDTO body) {
        User newUser =  userRepository.findByUsername(body.getUserName());

        BaseResponse response = new BaseResponse<RegisterResponse>();

        if(newUser != null){
            response.setCode("123456");
            response.setMessage("User already existed");
            return null;
        }

        userRepository.createUser(newUser);
//        Optional<User> user = Optional.of(newUser);
//        if (user.isPresent()){
//            return null;
//        }

        response.setCode("000000");
        response.setMessage("success");
        //create token, add token
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setToken("02124465465");
        response.setBody(registerResponse);

        return response;
    }
}
