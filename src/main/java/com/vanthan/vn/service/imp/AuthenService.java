package com.vanthan.vn.service.imp;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.RegisterDTO;
import com.vanthan.vn.dto.RegisterResponse;
import com.vanthan.vn.model.User;
import com.vanthan.vn.repository.UserRepository;
import com.vanthan.vn.service.ImpAuthen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenService implements ImpAuthen {
    @Autowired
    private UserRepository userRepository;

    @Override
    public BaseResponse<RegisterResponse> register(RegisterDTO body) {
        User user = new User();
        BaseResponse response = new BaseResponse<RegisterResponse>();
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
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setToken("02124465465");
        response.setBody(registerResponse);
        return response;
    }
}
