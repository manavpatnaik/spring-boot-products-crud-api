package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository
                            .findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Product with ID: " + id + " does not exist"));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        boolean exists = productRepository.existsById(id);
        if (!exists) throw new IllegalArgumentException("Product with id: " + id + " does not exist");
        productRepository.deleteById(id);
    }

    @Transactional
    public void updateProduct(Long id, String product_name, LocalDate expiry_date) {
        boolean exists = productRepository.existsById(id);
        if (!exists) throw new IllegalArgumentException("Product with id: " + id + " does not exist");
        Product product = productRepository
                            .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product with ID: " + id + " does not exist"));
        System.out.println(product);
        System.out.println("Found product to update: " + product.getId());
        System.out.println(product_name + " " + expiry_date);
        if (product_name != null && !product_name.equals(product.getProduct_name())) product.setProduct_name(product_name);
        if (expiry_date != null && expiry_date != product.getExpiry_date()) product.setExpiry_date(expiry_date);
        System.out.println(product);
    }
}
