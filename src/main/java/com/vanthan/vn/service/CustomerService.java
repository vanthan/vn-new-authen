package com.vanthan.vn.service;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface CustomerService {

    BaseResponse<String> saveCustomer(Customer body);

    BaseResponse<Page<Customer>>  findCustomer(PageRequest pageRequest);

    void deleteCustomer(Integer id);

    void updateCustomer(Customer body) throws Exception;

    BaseResponse<Page<Customer>> getCustomerByUserName(String userName,PageRequest pageRequest);

    BaseResponse<Optional<Customer>> findById(Integer id);
}
