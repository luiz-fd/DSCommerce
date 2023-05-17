package com.luizfd.DSCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luizfd.DSCommerce.entities.Product;

public interface ProductRepository extends JpaRepository< Product, Long> {

}
