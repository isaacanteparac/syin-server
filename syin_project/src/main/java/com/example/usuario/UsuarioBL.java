package com.example.usuario;

import java.sql.SQLException;
import java.util.List;

import com.example.utilidades.Helpers;


public class UsuarioBL {
	public static List<Usuario> index() throws SQLException  {
		return Usuario.index();
	}

	public static Usuario show(Integer id) throws SQLException  {
		return new Usuario(id);
	}

	public static Usuario usuario_perfil(Integer id, Integer id_organizaciones) throws SQLException {
		
		return new Usuario(id, id_organizaciones);
	}

	public static Usuario create(String json) throws SQLException {
		Usuario req=  (Usuario) Helpers.JsonToObject(json, Usuario.class);
		Usuario usuario=new Usuario();
		usuario.setApellidos(req.getApellidos());
		usuario.setNombres(req.getNombres());
		usuario.setCorreo(req.getCorreo());
		usuario.setId_perfiles(req.getId_perfiles());
		usuario.setId_organizaciones(req.getId_organizaciones());
		usuario.setId_ctlg_estados(req.getId_ctlg_estados());


	
		return usuario.save();
	}
	
	
	

	public static Usuario update(Integer id,String json) throws SQLException {
		Usuario req=  (Usuario) Helpers.JsonToObject(json, Usuario.class);
		Usuario usuario=new Usuario(id);
		usuario.setApellidos(req.getApellidos());
		usuario.setNombres(req.getNombres());
		usuario.setCorreo(req.getCorreo());
		usuario.setId_perfiles(req.getId_perfiles());
		usuario.setId_organizaciones(req.getId_organizaciones());
		usuario.setId_ctlg_estados(req.getId_ctlg_estados());

		
		return usuario.save();
	}

	public static Usuario delete(Integer id) throws SQLException  {
		Usuario usuario=new Usuario(id);
		return usuario.save();
	}


}
