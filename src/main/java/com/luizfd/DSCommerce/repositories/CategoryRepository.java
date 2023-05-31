package com.luizfd.DSCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luizfd.DSCommerce.entities.Category;

public interface CategoryRepository extends JpaRepository< Category, Long> {
	
}
