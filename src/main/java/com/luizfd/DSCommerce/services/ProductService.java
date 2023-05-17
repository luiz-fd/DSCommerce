package com.luizfd.DSCommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luizfd.DSCommerce.dto.ProductDTO;
import com.luizfd.DSCommerce.entities.Product;
import com.luizfd.DSCommerce.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> resultado = productRepository.findById(id);
		Product product = resultado.get();
		ProductDTO productDto = new ProductDTO(product);
		return productDto;
		
	}

}
