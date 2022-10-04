package com.vanthan.vn.service.impl;

import com.vanthan.vn.model.Customer;
import com.vanthan.vn.repository.CustomerRepository;
import com.vanthan.vn.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void saveCustomer(Customer customer){
        customerRepository.save(customer);
    }

   @Override
   public Page<Customer> findCustomer(){
        return customerRepository.findAll(PageRequest.of(0, 2));
   }
}
