package com.vanthan.vn.service.imp;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.RegisterForm;
import com.vanthan.vn.dto.RegisterResult;
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
    public BaseResponse<RegisterResult> register(RegisterForm body) {
        User newUser =  userRepository.findByUsername(body.getUserName());

        BaseResponse<RegisterResult> response = new BaseResponse<>();

        if(newUser != null){
            response.setCode("111111");
            response.setMessage("User already existed");
            return response;
        }

        userRepository.createUser(newUser);
//        Optional<User> user = Optional.of(newUser);
//        if (user.isPresent()){
//            return null;
//        }

        response.setCode("000000");
        response.setMessage("Successfully created!");
        //create token, add token
        RegisterResult registerResponse = new RegisterResult();
        registerResponse.setToken("02124465465");
        response.setBody(registerResponse);

        return response;
    }
}
