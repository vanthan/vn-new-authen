package com.vanthan.vn.service.impl;

import com.vanthan.vn.model.UserToken;
import com.vanthan.vn.repository.UserTokenRespository;
import com.vanthan.vn.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private UserTokenRespository userTokenRespository;

    @Override
    public void saveToken(UserToken token) {
        userTokenRespository.save(token);
    }
}
