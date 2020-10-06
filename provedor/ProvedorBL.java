package com.example.provedor;

import java.sql.SQLException;
import java.util.List;

import com.example.utilidades.Helpers;


public class ProvedorBL {
	public static List<Provedor> index() throws SQLException  {
		return Provedor.index();
	}

	public static Provedor show(Integer id) throws SQLException  {
		return new Provedor(id);
	}


	public static Provedor create(String json) throws SQLException {
		Provedor req=  (Provedor) Helpers.JsonToObject(json, Provedor.class);
		Provedor provedor=new Provedor();
		provedor.setNombre(req.getNombre());
		provedor.setTelefono(req.getTelefono());
		provedor.setCorreo(req.getCorreo());
		provedor.setUbicacion(req.getUbicacion());
		provedor.setId_organizacion(req.getId_organizacion());
		provedor.setId_ctlg_estados(req.getId_ctlg_estados());


	
		return provedor.save();
	}


	public static Provedor update(Integer id,String json) throws SQLException {
		Provedor req=  (Provedor) Helpers.JsonToObject(json, Provedor.class);
		Provedor provedor=new Provedor(id);
		provedor.setNombre(req.getNombre());
		provedor.setTelefono(req.getTelefono());
		provedor.setCorreo(req.getCorreo());
		provedor.setUbicacion(req.getUbicacion());
		provedor.setId_organizacion(req.getId_organizacion());
		provedor.setId_ctlg_estados(req.getId_ctlg_estados());

		
		return provedor.save();
	}


}
