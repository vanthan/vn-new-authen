package com.vanthan.vn.repository;

import com.vanthan.vn.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
//    Optional<List<Customer>> findTop20ByOrderById();

    Customer findByUserNameAndEmail(String userName, String email);
//    @Modifying
//    @Query("UPDATE Customer c SET c.userName = :userName, c.email = :email, c.age = :age WHERE c.id = :id")
//    void updateCustomer(@Param("id") Integer id, @Param("userName") String userName, @Param("email") String email, @Param("age") String age);
//
    Optional<Customer> findById(Integer id);

}
