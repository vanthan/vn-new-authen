package com.vanthan.vn.repository;

import com.vanthan.vn.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    List<Product> findBySku(String sku);
}
