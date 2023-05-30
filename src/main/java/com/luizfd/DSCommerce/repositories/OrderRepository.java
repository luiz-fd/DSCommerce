package com.luizfd.DSCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luizfd.DSCommerce.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	
}
