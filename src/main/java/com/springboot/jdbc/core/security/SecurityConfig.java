package com.springboot.jdbc.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(getPasswordEncoder());
		
		/*
		 * aqui digo a spring que ante cualquier intento de autentificacion , llame a la
		 * implemnteacion de userDetailService que yo he creado ,para comprobar si
		 * coinciden las credenciales entradas por el usuario con las obtenidas por mi
		 * servicio
		 */
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
		.antMatchers("/empresa/").hasAnyRole("USER","ADMIN")
		.antMatchers("/empresa/estado/**").hasAnyRole("USER","ADMIN")
		.antMatchers("/empresa/modulos/**").hasAnyRole("USER","ADMIN")
		.antMatchers("/empresa/resetPassword/**").hasRole("ADMIN")
		.antMatchers("/empresa/cambiaEstado/**").hasRole("ADMIN")
		.antMatchers("/css/**").permitAll()
		.antMatchers("/js/**").permitAll()
		.antMatchers("/img/**").permitAll()
		.antMatchers("/user/crear").permitAll()
		.antMatchers("/").permitAll()
		.and().formLogin();
	}
	
	
}
