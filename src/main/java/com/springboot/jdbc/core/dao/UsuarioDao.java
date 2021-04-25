package com.springboot.jdbc.core.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.springboot.jdbc.core.entity.UsuarioAssets;
import com.springboot.jdbc.core.mapper.UsuarioAssetsMapper;

@Service
public class UsuarioDao {
	
	private final JdbcTemplate jdbcTemplate;

	public UsuarioDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<UsuarioAssets>listarUsuariosAssets(){
		return jdbcTemplate
			.query("Select distinct Id_Comercial,Desc_Comercial,CI,Id_User,AssetsUser FROM dbo.Empleado where Id_User!='' order by Id_User", new UsuarioAssetsMapper());
	}
	
	public UsuarioAssets verDetalles(String idUser) {
		return jdbcTemplate
				.queryForObject("Select Id_Comercial,Desc_Comercial,CI,Id_User,AssetsUser FROM dbo.Empleado where Id_User=?",
						new UsuarioAssetsMapper(),idUser);
	}
	
	public int resetPassword(UsuarioAssets usr) {
		 return jdbcTemplate
				 .update("DELETE dbo.Formato_XXL WHERE fld001='"+usr.getUsuarioAssets()+"'");
	}
	
	public int cambiarEstado(UsuarioAssets usr,boolean estado) {
		return jdbcTemplate
				.update("UPDATE dbo.Empleado set AssetsUser=? WHERE Id_User='"+usr.getUsuarioAssets()+"'", estado);
	}
}
