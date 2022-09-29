package com.vanthan.vn.repository;

import com.vanthan.vn.model.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRespository extends CrudRepository<Token, Integer> {
}
