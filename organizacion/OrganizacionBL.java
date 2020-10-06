package com.example.organizacion;

import java.sql.SQLException;
import java.util.List;

import com.example.organizacion.Organizacion;
import com.example.utilidades.Helpers;

public class OrganizacionBL {
	public static List<Organizacion> index() throws SQLException  {
		return Organizacion.index();
	}

	public static Organizacion show(Integer id) throws SQLException  {
		return new Organizacion(id);
	}


	public static Organizacion create(String json) throws SQLException {
		Organizacion req=  (Organizacion) Helpers.JsonToObject(json, Organizacion.class);
		Organizacion organizacion=new Organizacion();
		organizacion.setNombre(req.getNombre());
		organizacion.setCorreo(req.getCorreo());
		organizacion.setTelefono(req.getTelefono());
		organizacion.setUbicacion(req.getUbicacion());
		organizacion.setId_ctlg_estados(req.getId_ctlg_estados());

	
		return organizacion.save();
	}


	public static Organizacion update(Integer id,String json) throws SQLException {
		Organizacion req=  (Organizacion) Helpers.JsonToObject(json, Organizacion.class);
		Organizacion organizacion=new Organizacion(id);
		organizacion.setNombre(req.getNombre());
		organizacion.setCorreo(req.getCorreo());
		organizacion.setTelefono(req.getTelefono());
		organizacion.setUbicacion(req.getUbicacion());
		organizacion.setId_ctlg_estados(req.getId_ctlg_estados());
		return organizacion.save();
	}

}

