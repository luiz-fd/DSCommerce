package com.luizfd.DSCommerce.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luizfd.DSCommerce.dto.OrderDTO;
import com.luizfd.DSCommerce.dto.OrderItemDTO;
import com.luizfd.DSCommerce.entities.Order;
import com.luizfd.DSCommerce.entities.OrderItem;
import com.luizfd.DSCommerce.entities.OrderStatus;
import com.luizfd.DSCommerce.entities.Product;
import com.luizfd.DSCommerce.entities.User;
import com.luizfd.DSCommerce.repositories.OrderItemRepository;
import com.luizfd.DSCommerce.repositories.OrderRepository;
import com.luizfd.DSCommerce.repositories.ProductRepository;
import com.luizfd.DSCommerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private UserService userService;
	
	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		Order order = orderRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recurso não encontrado"));
		OrderDTO orderDto = new OrderDTO(order);
		return orderDto;		
	}

	@Transactional
	public OrderDTO insert(OrderDTO dto) {
		Order order = new Order();
		order.setMoment(Instant.now());
		order.setStatus(OrderStatus.WAITING_PAYMENT);
		User user = userService.authenticated();
		order.setClient(user);
		for(OrderItemDTO itemDTO : dto.getItems()) {
			Product product = productRepository.getReferenceById(itemDTO.getProductId());
			OrderItem item = new OrderItem(order,product,itemDTO.getQuantity(),product.getPrice());
			order.getItems().add(item);
		}
		
		orderRepository.save(order);
		orderItemRepository.saveAll(order.getItems());
		return new OrderDTO(order);
	}

}
