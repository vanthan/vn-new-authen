package com.vanthan.vn.repository;

import com.vanthan.vn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository  extends JpaRepository<User, Integer> {
//    UserDTO findById();
    User findByEmail(String email);
}
