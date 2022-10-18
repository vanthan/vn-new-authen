package com.vanthan.vn.service.impl;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.model.entity.Customer;
import com.vanthan.vn.repository.CustomerRepository;
import com.vanthan.vn.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public BaseResponse<String> saveCustomer(Customer body) {
        Customer customer = new Customer();
        BaseResponse response = new BaseResponse<String>();
        //check by userName and Email
        customer = customerRepository.findByUserNameAndEmail(body.getUserName(), body.getEmail());
        if (customer != null) {
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
    public BaseResponse<Optional<Customer>> findById(Integer id) {
        BaseResponse response = new BaseResponse();
        response.setBody(customerRepository.findById(id));
//        response.setCode("00");

        return response;

    }

    @Override
    public BaseResponse<Page<Customer>> findCustomer(PageRequest pageRequest) {
        BaseResponse rs = new BaseResponse();
        rs.setBody(customerRepository.findAll(pageRequest));
//        rs.setCode("00");

        return rs;
    }

    @Override
    public BaseResponse<Customer> deleteCustomer(Integer id) {
        BaseResponse rs = new BaseResponse();
        customerRepository.deleteById(id);
        rs.setMessage("Delete success!");

        return rs;
    }

    @Override
    public BaseResponse<Customer> updateCustomer(Customer body) throws Exception {
        Customer customer = customerRepository.findById(body.getId())
                .orElseThrow(() -> new Exception("not_found"));

        customer.setUserName(body.getUserName());
        customer.setEmail(body.getEmail());
        customer.setAge(body.getAge());

        BaseResponse rs = new BaseResponse();
        rs.setBody(customerRepository.save(customer));
        rs.setMessage("Update Customer successfully");

        return rs;

    }

    @Override
    public BaseResponse<Page<Customer>> getCustomerByUserName(String userName, PageRequest pageRequest) {
        BaseResponse rs = new BaseResponse();
        rs.setBody(customerRepository.getCustomerByUserName(userName, pageRequest));
//        rs.setCode("00");

        return rs;
    }

}
