package com.vanthan.vn.repository;

import com.vanthan.vn.model.entity.UserToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenRespository extends CrudRepository<UserToken, Integer> {
}
