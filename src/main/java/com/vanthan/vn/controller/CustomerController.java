package com.vanthan.vn.controller;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.model.Customer;
import com.vanthan.vn.model.Paging;
import com.vanthan.vn.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/saveCustomer")
    public ResponseEntity<BaseResponse<String>> saveCustomer(@RequestBody Customer body) {
        return ResponseEntity.ok(customerService.saveCustomer(body));
    }
//    @GetMapping("/getCustomer")
//    public Page<Customer> findCustomer(@RequestParam(value = "page") int pageNo, @RequestParam(value = "totalNum") int totalNum){
//        PageRequest pageRequest = PageRequest.of(pageNo, totalNum);
//        return customerService.findCustomer(pageRequest);
//    }

    @GetMapping("/customerDetail/{id}")
    public ResponseEntity<BaseResponse<Optional<Customer>>> findById(@PathVariable Integer id) {
        BaseResponse<Optional<Customer>> response = customerService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/getCustomer")
    public ResponseEntity<BaseResponse<Page<Customer>>> findCustomer(@RequestBody Paging body) {
        Paging paging = new Paging();
        paging.setPageNum(body.getPageNum());
        paging.setTotalNum(body.getTotalNum());
        PageRequest pageRequest = PageRequest.of(body.getPageNum(), body.getTotalNum());
        BaseResponse<Page<Customer>> rs = customerService.findCustomer(pageRequest);
        return ResponseEntity.ok(rs);

    }

    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<BaseResponse<Customer>> deleteCutomer(@PathVariable Integer id) {
        BaseResponse<Customer> rs = customerService.deleteCustomer(id);
        return ResponseEntity.ok(rs);
        
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<BaseResponse<Customer>> updateCustomer(@RequestBody Customer body) throws Exception {
        BaseResponse<Customer> rs = customerService.updateCustomer(body);
        return ResponseEntity.ok(rs);
    }

    @PostMapping("/search-userName")
    public ResponseEntity<BaseResponse<Page<Customer>>> getCustomerByUserName(@RequestParam(value = "userName") String userName, @RequestBody Paging paging) {
        Paging paging1 = new Paging();
        paging1.setPageNum(paging.getPageNum());
        paging1.setTotalNum(paging.getTotalNum());
        PageRequest pageRequest = PageRequest.of(paging.getPageNum(), paging.getTotalNum());
        BaseResponse<Page<Customer>> rs = customerService.getCustomerByUserName(userName, pageRequest);

        return ResponseEntity.ok(rs);
    }
}
