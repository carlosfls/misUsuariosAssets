package com.springboot.jdbc.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springboot.jdbc.core.entity.UsuarioAssets;

public class UsuarioAssetsMapper implements RowMapper<UsuarioAssets>{

	@Override
	public UsuarioAssets mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new UsuarioAssets(
				rs.getString("Id_Comercial"),
				rs.getString("Desc_Comercial"),
				rs.getString("CI"),
				rs.getString("Id_User"),
				rs.getBoolean("AssetsUser")
				);
	}

}
