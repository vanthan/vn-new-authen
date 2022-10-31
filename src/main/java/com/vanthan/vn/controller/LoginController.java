package com.vanthan.vn.controller;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.LoginResponse;
import com.vanthan.vn.dto.UserForm;
import com.vanthan.vn.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class LoginController extends BaseController {

    @Autowired
    LoginService loginService;

    @PostMapping(value = "/login")
    public ResponseEntity<BaseResponse<LoginResponse>> login(@RequestBody UserForm body){

        try {
            return ResponseEntity.ok(loginService.checkLogin(body));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(null, e.getMessage()));
        }
    }
}
