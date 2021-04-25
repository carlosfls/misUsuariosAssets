package com.springboot.jdbc.core.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.jdbc.core.entity.User;
import com.springboot.jdbc.core.repo.UserRepo;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("crear")
	public String getUserForm(@ModelAttribute("usuario")User u) {
		return "userForm";
	}
	
	@PostMapping("crear")
	public String crearUsuario(@Valid @ModelAttribute("usuario")User u) {
		u.setEnabled(true);
		u.setAccountNonExpired(true);
		u.setAccountNonLocked(true);
		u.setCredentialsNonExpired(true);
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		
		userRepo.save(u);
		
		return "redirect:/login";
	}
	
	@ModelAttribute("roles")
	public List<String>getRoles(){
		List<String>roles=new ArrayList<String>();
		roles.add("ROLE_USER");
		roles.add("ROLE_ADMIN");
		
		return roles;
	}
	
}
