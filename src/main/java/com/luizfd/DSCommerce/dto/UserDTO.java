package com.luizfd.DSCommerce.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.luizfd.DSCommerce.entities.User;

public class UserDTO {
	
	private Long id;
	private String name;
	private String email;
	private String phone;
	private LocalDate birthDate;
	
	private List<String> roles = new ArrayList<>();

	public UserDTO(User entity) {
		this.id = entity.getId();
		this.name = entity.getNome();
		this.email = entity.getEmail();
		this.phone = entity.getPhone();
		this.birthDate = entity.getBirhtDate();
		for(GrantedAuthority role: entity.getRoles()) {
			roles.add(role.getAuthority());
		}
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public List<String> getRoles() {
		return roles;
	}
	
	
	
	

}
