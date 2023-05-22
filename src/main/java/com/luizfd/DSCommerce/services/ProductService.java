package com.luizfd.DSCommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.luizfd.DSCommerce.dto.ProductDTO;
import com.luizfd.DSCommerce.entities.Product;
import com.luizfd.DSCommerce.repositories.ProductRepository;
import com.luizfd.DSCommerce.services.exceptions.DataBaseException;
import com.luizfd.DSCommerce.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Product product = productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recurso não encontrado"));
		ProductDTO productDto = new ProductDTO(product);
		return productDto;		
	}

	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(String name, Pageable pageable) {
		Page<Product> resultado = productRepository.searchByName(name, pageable);
		return resultado.map(x -> new ProductDTO(x));		
	}

	@Transactional()
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		copyDtoToEntity(dto,entity);
		entity = productRepository.save(entity);
		return new ProductDTO(entity);
	}

	@Transactional()
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = productRepository.getReferenceById(id);
			copyDtoToEntity(dto,entity);
			entity = productRepository.save(entity);
			return new ProductDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if (!productRepository.existsById(id)) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		try {
			productRepository.deleteById(id);    		
		}
    	catch (DataIntegrityViolationException e) {
    		System.out.println("Falha: " + e.getCause());
        	throw new DataBaseException("Falha de integridade referencial");
	   	}
	}

	private void copyDtoToEntity(ProductDTO dto, Product entity){
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());
	}

}
