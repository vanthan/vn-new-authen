package com.vanthan.vn.repository;

import com.vanthan.vn.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    //List<User> findById();
    User findByUsername(String username);
}
