package com.vanthan.vn.controller;

import com.vanthan.vn.dto.BaseResponse;

import com.vanthan.vn.dto.RegisterForm;
import com.vanthan.vn.dto.RegisterResult;
import com.vanthan.vn.service.ImpAuthen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private ImpAuthen authen;

    @PostMapping(value = "/register")
    public ResponseEntity<BaseResponse<RegisterResult>> register(@RequestBody RegisterForm body){
        return ResponseEntity.ok(authen.register(body));
    }


}
