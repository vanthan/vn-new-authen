package com.vanthan.vn.service.user.impl;

import com.vanthan.vn.model.Token;
import com.vanthan.vn.repository.TokenRespository;
import com.vanthan.vn.service.user.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRespository tokenRespository;

    @Override
    public void saveToken(Token token){
        tokenRespository.save(token);
    }
}
