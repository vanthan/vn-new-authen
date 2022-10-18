package com.vanthan.vn.repository;

import com.vanthan.vn.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRespository extends JpaRepository<User, Integer> {
    User findByEmailAndPassword(String email, String password);

    Optional<User> findById(Integer id);
}
