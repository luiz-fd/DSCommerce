package com.luizfd.DSCommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luizfd.DSCommerce.dto.OrderDTO;
import com.luizfd.DSCommerce.entities.Order;
import com.luizfd.DSCommerce.repositories.OrderRepository;
import com.luizfd.DSCommerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		Order order = orderRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recurso não encontrado"));
		OrderDTO orderDto = new OrderDTO(order);
		return orderDto;		
	}

}
