package com.vanthan.vn.repository;

import com.vanthan.vn.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    //List<User> findById();
    User findByUsername(String username);
    boolean createUser(User user);
}
