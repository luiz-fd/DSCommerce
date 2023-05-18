package com.luizfd.DSCommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(Pageable pageable) {
		Page<Product> resultado = productRepository.findAll(pageable);
		return resultado.map(x -> new ProductDTO(x));		
	}

	@Transactional()
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());
		entity = productRepository.save(entity);
		return new ProductDTO(entity);
	}

}
