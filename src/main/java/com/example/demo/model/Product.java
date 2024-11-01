package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "product_sequence"
    )
    private Long id;
    private String product_name;
    private Long manufacturer_id;
    private LocalDate manufactured_date;
    private LocalDate expiry_date;
    @Transient
    private int shelf_life;
    @Transient
    private int days_to_expiry;

    public Product() {
    }

    public Product(Long id, String product_name, Long manufacturer_id, LocalDate manufactured_date, LocalDate expiry_date, int shelf_life, int days_to_expiry) {
        this.id = id;
        this.product_name = product_name;
        this.manufacturer_id = manufacturer_id;
        this.manufactured_date = manufactured_date;
        this.expiry_date = expiry_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Long getManufacturer_id() {
        return manufacturer_id;
    }

    public void setManufacturer_id(Long manufacturer_id) {
        this.manufacturer_id = manufacturer_id;
    }

    public LocalDate getManufactured_date() {
        return manufactured_date;
    }

    public void setManufactured_date(LocalDate manufactured_date) {
        this.manufactured_date = manufactured_date;
    }

    public LocalDate getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(LocalDate expiry_date) {
        this.expiry_date = expiry_date;
    }

    public int getShelf_life() {
        return (int) ChronoUnit.DAYS.between(manufactured_date, expiry_date);
    }

    public int getDays_to_expiry() {
        return (int) ChronoUnit.DAYS.between(LocalDate.now(), expiry_date);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", product_name='" + product_name + '\'' +
                ", manufacturer_id=" + manufacturer_id +
                ", manufactured_date=" + manufactured_date +
                ", expiry_date=" + expiry_date +
                ", shelf_life=" + shelf_life +
                ", days_to_expiry=" + days_to_expiry +
                '}';
    }
}
