package com.springboot.jdbc.core.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.jdbc.core.entity.User;
import com.springboot.jdbc.core.repo.UserRepo;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User>userBusq = userRepo.findByUsername(username);
		if(userBusq.isPresent()) {
			User u=userBusq.get();
			
			List<SimpleGrantedAuthority>permisos = new ArrayList<SimpleGrantedAuthority>();
			permisos.add(new SimpleGrantedAuthority(userBusq.get().getRol()));
			
			return new org.springframework.security.core.userdetails.User
				(
					u.getUsername(),
					u.getPassword(),
					u.isEnabled(),
					u.isAccountNonExpired(),
					u.isCredentialsNonExpired(),
					u.isAccountNonLocked(),
					permisos		
				);
			
		}else {
			throw new UsernameNotFoundException(username);
		}
	}
}
