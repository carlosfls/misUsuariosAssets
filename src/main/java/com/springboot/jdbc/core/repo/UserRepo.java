package com.springboot.jdbc.core.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.jdbc.core.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{
	
	Optional<User>findByUsername(String username);
}
