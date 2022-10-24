package com.vanthan.vn.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vanthan.vn.model.TransactionDetail;


public class Utils {
    ObjectMapper objectMapper = new ObjectMapper();

    public String convertObjectToJson(Object o){
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
