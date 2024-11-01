package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.expiry_date < ?1")
    Optional<List<Product>> getProductsBeforeExpiry(LocalDate date);

    @Query("SELECT p FROM Product p WHERE p.manufacturer_id = ?1")
    Optional<List<Product>> getProductsByManufacturer(Long id);
}
