package com.vanthan.vn.controller;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.dto.ProductForm;
import com.vanthan.vn.dto.RegisterResult;
import com.vanthan.vn.model.Customer;
import com.vanthan.vn.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/saveCustomer")
    public ResponseEntity<BaseResponse<String >> saveCustomer(@RequestBody Customer body){
        return ResponseEntity.ok(customerService.saveCustomer(body));
    }
    @GetMapping("/getCustomer")
    public Page<Customer> findCustomer(@RequestParam(value = "page") int pageNo, @RequestParam(value = "totalNum") int totalNum){
        PageRequest pageRequest = PageRequest.of(pageNo, totalNum);
        return customerService.findCustomer(pageRequest);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public void deleteCutomer(@PathVariable Integer id){
        customerService.deleteCustomer(id);
    }

    @PutMapping("/updateCustomer")
    public void updateCustomer(@RequestBody Customer body) throws Exception {
        customerService.updateCustomer(body);
    }
}
