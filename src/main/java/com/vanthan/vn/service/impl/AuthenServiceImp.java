package com.vanthan.vn.service.impl;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.RegisterForm;
import com.vanthan.vn.dto.RegisterResult;
import com.vanthan.vn.dto.UserResult;
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
 
    private UserRepository userRepository;
    private UserTokenRespository tokenRespository;
    JwtUtils jwtUtils;
    
    @Autowired
    public AuthenServiceImp(UserRepository userRepository, UserTokenRespository tokenRespository, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.tokenRespository = tokenRespository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public BaseResponse<RegisterResult> register(RegisterForm body) {
        User user = new User();
        BaseResponse response = new BaseResponse<RegisterResult>();
        //check user by username
        user = userRepository.findByUsername(body.getUsername());
        if (user != null) {
            response.setCode("001");
            response.setMessage("Username already existed");
            return response;
        }

        user = userRepository.findByEmail(body.getEmail());
        if (user != null) {
            response.setCode("001");
            response.setMessage("Email already existed");
            return response;
        }

        // get string from form to validate
        String password = body.getPassword();
        String upperCaseChars = "(.*[A-Z].*)";
        String lowerCaseChars = "(.*[a-z].*)";
        String numbers = "(.*[0-9].*)";
        String specialChars = "(.*[@,#,$,%].*$)";
        if (password.length() > 15 || password.length() < 8) {
            response.setCode("11");
            response.setMessage("Password must be less than 20 and more than 8 characters in length.");
        } else if (!password.matches(upperCaseChars)) {
            response.setCode("12");
            response.setMessage("Password must have at least one uppercase character.");
        } else if (!password.matches(lowerCaseChars)) {
            response.setCode("13");
            response.setMessage("Password must have at least one lowercase character.");
        } else if (!password.matches(numbers)) {
            response.setCode("14");
            response.setMessage("Password must have at least one number.");
        } else if (!password.matches(specialChars)) {
            response.setCode("15");
            response.setMessage("Password must have at least one special character among @#$%.");
        } else {
            //create new user
            user = new User();
            user.setFullName(body.getFullName());
            user.setEmail(body.getEmail());
            user.setUsername(body.getUsername());
            user.setPassword(body.getPassword());

            response.setCode("00");
            response.setMessage("Successfully created a new user!");
            // save the result into db

            userRepository.save(user);
            //create token, add token
            RegisterResult registerResult = new RegisterResult();
            // result token
            UserResult userResult = new UserResult();
            userResult.setUserName(body.getUsername());
            userResult.setUserId(user.getId());
            userResult.setEmail(user.getEmail());
            userResult.setPassword(user.getPassword());
            registerResult.setToken(jwtUtils.generateJwtToken(userResult));

            UserToken userToken = new UserToken();
            userToken.setToken(registerResult.getToken());
            userToken.setUserId(user.getId());
            tokenRespository.save(userToken);

            response.setBody(registerResult);
        }

        return response;
    }

}
