package com.vanthan.vn.service.impl;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.LoginResponse;
import com.vanthan.vn.dto.UserDTO;
import com.vanthan.vn.model.User;
import com.vanthan.vn.repository.LoginRepository;
import com.vanthan.vn.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

    private LoginRepository loginRepository;

    @Override
    public BaseResponse<LoginResponse> checkLogin(UserDTO body){
        BaseResponse<LoginResponse> response = new BaseResponse<>();
        //check email exits
        try {
            User user = loginRepository.findByEmail(body.getEmail());
            if (user == null){
                throw new Exception("Không tồn tại email");
            }
        }catch (Exception e){
            response.setMessage(e.getMessage());
        }

        // sinh token

//        for (UserDTO userDTO : loginRepository.findById()){
//            if(userDTO.getEmail().equals(email) && userDTO.getPassword().equals(password)){
//
//            }
//        }
        return response;
    }
}
