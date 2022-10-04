package com.vanthan.vn.repository;

import com.vanthan.vn.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerResppository extends JpaRepository<Customer, Integer> {


}
