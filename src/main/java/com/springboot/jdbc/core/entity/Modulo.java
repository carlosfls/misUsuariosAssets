package com.springboot.jdbc.core.entity;

public class Modulo {

	private String Id_user;
	private String Id_Modulo;
	private String Descripcion;
	private String Acceso;

	public Modulo() {
	}

	public Modulo(String id_user, String id_Modulo, String descripcion, String acceso) {
		super();
		Id_user = id_user;
		Id_Modulo = id_Modulo;
		Descripcion = descripcion;
		Acceso = acceso;
	}

	public String getId_user() {
		return Id_user;
	}

	public void setId_user(String id_user) {
		Id_user = id_user;
	}

	public String getId_Modulo() {
		return Id_Modulo;
	}

	public void setId_Modulo(String id_Modulo) {
		Id_Modulo = id_Modulo;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public String getAcceso() {
		return Acceso;
	}

	public void setAcceso(String acceso) {
		Acceso = acceso;
	}

	@Override
	public String toString() {
		return "Modulo [Id_user=" + Id_user + ", Id_Modulo=" + Id_Modulo + ", Descripcion=" + Descripcion + ", Acceso="
				+ Acceso + "]";
	}

}
