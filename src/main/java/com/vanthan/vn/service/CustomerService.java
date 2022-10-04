package com.vanthan.vn.service;

import com.vanthan.vn.model.Customer;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    void saveCustomer(Customer customer);

    Page<Customer> findCustomer();


}
