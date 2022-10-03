package com.vanthan.vn.repository;

import com.vanthan.vn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRespository extends JpaRepository<User, Integer> {
    User findByEmailAndPassword(String email, String password);
}
