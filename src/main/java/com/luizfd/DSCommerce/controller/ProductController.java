package com.luizfd.DSCommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luizfd.DSCommerce.dto.ProductDTO;
import com.luizfd.DSCommerce.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
		
	@GetMapping(value = "/{id}")
	public ProductDTO findById(@PathVariable Long id) {		
		//return "Olá mundo!";
		
		return productService.findById(id);
	}

}
