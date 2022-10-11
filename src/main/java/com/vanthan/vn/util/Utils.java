package com.vanthan.vn.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vanthan.vn.model.User;
import com.vanthan.vn.service.LoginService;
import org.springframework.security.core.Authentication;

public class Utils {
    static  ObjectMapper objectMapper = new ObjectMapper();

    public static String convertObjectToString(Object o){
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
