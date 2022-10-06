package com.vanthan.vn.service;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.RegisterForm;
import com.vanthan.vn.dto.RegisterResult;
import com.vanthan.vn.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface CustomerService {

    BaseResponse<String> saveCustomer(Customer body);

    Page<Customer> findCustomer(PageRequest pageRequest);

    void deleteCustomer(Integer id);

    void updateCustomer(Customer body) throws Exception;


}
