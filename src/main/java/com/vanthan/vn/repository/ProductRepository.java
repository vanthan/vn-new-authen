package com.vanthan.vn.repository;

import com.vanthan.vn.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findBySku(String sku);
//    Optional<List<Product>> findTop3ByOrderBySku();
}
