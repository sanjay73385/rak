package com.pinkaura.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.pinkaura.model.Product;
import com.pinkaura.repository.ProductRepository;

@RestController
@CrossOrigin
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }
}