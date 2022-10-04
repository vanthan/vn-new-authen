package com.vanthan.vn.controller;

import com.vanthan.vn.model.Customer;
import com.vanthan.vn.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/saveCustomer")
    public void saveCustomer(@RequestBody Customer customer){
        customerService.saveCustomer(customer);
    }
}
