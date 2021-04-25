package com.springboot.jdbc.core.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.springboot.jdbc.core.entity.Modulo;
import com.springboot.jdbc.core.mapper.ModuloMapper;

@Service
public class ModuloDao {
	
	private final JdbcTemplate jdbcTemplate;

	public ModuloDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Modulo>misModulos(String usr){
		return jdbcTemplate
			.query("select Id_User,Id_Modulo,Desc_Opcion,Acceso from MCVS_HABANA.dbo.Acceso where Id_User=?",
				new ModuloMapper(),usr);
	}
	
	public List<?> populateFilter(String usr) {
		return jdbcTemplate
				.queryForList("select distinct Acceso from Acceso where Id_User=?", usr);
	}
}
