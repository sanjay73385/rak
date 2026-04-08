package com.pinkaura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pinkaura.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}