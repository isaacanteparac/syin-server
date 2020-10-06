package com.example.perfil;

import java.sql.SQLException;
import java.util.List;

import com.example.perfil.Perfil;
import com.example.utilidades.Helpers;

public class PerfilBL {
	public static List<Perfil> index() throws SQLException  {
		return Perfil.index();
	}

	public static Perfil show(Integer id) throws SQLException  {
		return new Perfil(id);
	}


	public static Perfil create(String json) throws SQLException {
		Perfil req=  (Perfil) Helpers.JsonToObject(json, Perfil.class);
		Perfil perfil=new Perfil();
		perfil.setNombre(req.getNombre());
		perfil.setId_organizaciones(req.getId_organizaciones());
		perfil.setId_ctlg_estados(req.getId_ctlg_estados());


	
		return perfil.save();
	}


	public static Perfil update(Integer id,String json) throws SQLException {
		Perfil req=  (Perfil) Helpers.JsonToObject(json, Perfil.class);
		Perfil perfil=new Perfil(id);
		perfil.setNombre(req.getNombre());
		perfil.setId_organizaciones(req.getId_organizaciones());
		perfil.setId_ctlg_estados(req.getId_ctlg_estados());

		
		return perfil.save();
	}


}
