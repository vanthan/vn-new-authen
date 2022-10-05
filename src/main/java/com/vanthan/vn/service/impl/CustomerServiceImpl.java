package com.vanthan.vn.service.impl;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.RegisterResult;
import com.vanthan.vn.model.Customer;
import com.vanthan.vn.model.User;
import com.vanthan.vn.repository.CustomerRepository;
import com.vanthan.vn.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public BaseResponse<String> saveCustomer(Customer body){
        Customer customer = new Customer();
        BaseResponse response = new BaseResponse<String>();
        //check by userName and Email
        customer = customerRepository.findByUserNameAndEmail(body.getUserName(), body.getEmail());
        if (customer != null){
            response.setCode("001");
            response.setMessage("Customer already existed");
            return response;
        }
        customer = new Customer();
        customer.setUserName(body.getUserName());
        customer.setEmail(body.getEmail());
        customer.setAge(body.getAge());
        customerRepository.save(customer);

        response.setCode("00");
        response.setMessage("Successfully created a new Customer!");

        return response;
    }

   @Override
   public Page<Customer> findCustomer(PageRequest pageRequest){
        return customerRepository.findAll(pageRequest);
   }

   @Override
    public void deleteCustomer(Integer id){
        customerRepository.deleteById(id);
   }

   @Override
    public void updateCustomer(Integer id, String userName, String email, String age){
        customerRepository.updateCustomer(id, userName, email, age);
   }

}
