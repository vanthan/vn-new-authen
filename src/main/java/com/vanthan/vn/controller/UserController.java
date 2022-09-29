package com.vanthan.vn.controller;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.RegisterDTO;
import com.vanthan.vn.dto.RegisterResponse;
import com.vanthan.vn.service.ImpAuthen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private ImpAuthen authen;

    @PostMapping(value = "/register")
    public ResponseEntity<BaseResponse<RegisterResponse>> register(@RequestBody RegisterDTO body){
        return ResponseEntity.ok(authen.register(body));
    }
}
