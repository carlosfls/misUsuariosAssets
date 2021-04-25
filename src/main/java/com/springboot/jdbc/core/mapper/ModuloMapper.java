package com.springboot.jdbc.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springboot.jdbc.core.entity.Modulo;

public class ModuloMapper implements RowMapper<Modulo>{

	@Override
	public Modulo mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new Modulo(
				rs.getString("Id_User"),
				rs.getString("Id_Modulo"),
				rs.getString("Desc_Opcion"),
				rs.getString("Acceso")
				);
	}

}
