package com.vanthan.vn.service.impl;

import com.vanthan.vn.model.Customer;
import com.vanthan.vn.repository.CustomerResppository;
import com.vanthan.vn.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerResppository customerResppository;

    @Override
    public void saveCustomer(Customer customer){
        customerResppository.save(customer);
    }
}
