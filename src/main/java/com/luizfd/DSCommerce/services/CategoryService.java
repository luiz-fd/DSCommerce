package com.luizfd.DSCommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luizfd.DSCommerce.dto.CategoryDTO;
import com.luizfd.DSCommerce.entities.Category;
import com.luizfd.DSCommerce.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		List<Category> resultado = categoryRepository.findAll();
		return resultado.stream().map(x -> new CategoryDTO(x)).toList();		
	}


}
