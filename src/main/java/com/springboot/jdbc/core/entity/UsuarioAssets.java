package com.springboot.jdbc.core.entity;

public class UsuarioAssets {
	
	private String Id_Comercial;
	private String Desc_Comercial;
	private String CI;
	private String usuarioAssets;
    private boolean activo;
	
    public UsuarioAssets() {
	}

	public UsuarioAssets(String id_Comercial, String desc_Comercial, String cI, String usuarioAssets, boolean activo) {
		Id_Comercial = id_Comercial;
		Desc_Comercial = desc_Comercial;
		CI = cI;
		this.usuarioAssets = usuarioAssets;
		this.activo = activo;
	}

	public String getId_Comercial() {
		return Id_Comercial;
	}

	public void setId_Comercial(String id_Comercial) {
		Id_Comercial = id_Comercial;
	}

	public String getDesc_Comercial() {
		return Desc_Comercial;
	}

	public void setDesc_Comercial(String desc_Comercial) {
		Desc_Comercial = desc_Comercial;
	}

	public String getCI() {
		return CI;
	}

	public void setCI(String cI) {
		CI = cI;
	}

	public String getUsuarioAssets() {
		return usuarioAssets;
	}

	public void setUsuarioAssets(String usuarioAssets) {
		this.usuarioAssets = usuarioAssets;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "UsuarioAssets [Id_Comercial=" + Id_Comercial + ", Desc_Comercial=" + Desc_Comercial + ", CI=" + CI
				+ ", usuarioAssets=" + usuarioAssets + ", activo=" + activo + "]";
	}
    
    
    
}
