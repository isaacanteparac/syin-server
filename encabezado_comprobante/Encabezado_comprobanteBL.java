package com.example.encabezado_comprobante;

import java.sql.SQLException;
import java.util.List;

import com.example.utilidades.Helpers;


public class Encabezado_comprobanteBL {
	public static List<Encabezado_comprobante> index() throws SQLException  {
		return Encabezado_comprobante.index();
	}

	public static Encabezado_comprobante show(Integer id) throws SQLException  {
		return new Encabezado_comprobante(id);
	}


	public static Encabezado_comprobante create(String json) throws SQLException {
		Encabezado_comprobante req=  (Encabezado_comprobante) Helpers.JsonToObject(json, Encabezado_comprobante.class);
		Encabezado_comprobante encabezado_comprobante=new Encabezado_comprobante();
		encabezado_comprobante.setNum_comprovante(req.getNum_comprovante());
		encabezado_comprobante.setId_organizacion(req.getId_organizacion());
		encabezado_comprobante.setId_usuario(req.getId_usuario());
		encabezado_comprobante.setId_ctlg_tipos_comprobante(req.getId_ctlg_tipos_comprobante());
		encabezado_comprobante.setId_ctlg_tipos_transacion(req.getId_ctlg_tipos_transacion());



	
		return encabezado_comprobante.save();
	}


	public static Encabezado_comprobante update(Integer id,String json) throws SQLException {
		Encabezado_comprobante req=  (Encabezado_comprobante) Helpers.JsonToObject(json, Encabezado_comprobante.class);
		Encabezado_comprobante encabezado_comprobante=new Encabezado_comprobante(id);
		encabezado_comprobante.setNum_comprovante(req.getNum_comprovante());
		encabezado_comprobante.setId_organizacion(req.getId_organizacion());
		encabezado_comprobante.setId_usuario(req.getId_usuario());
		encabezado_comprobante.setId_ctlg_tipos_comprobante(req.getId_ctlg_tipos_comprobante());
		encabezado_comprobante.setId_ctlg_tipos_transacion(req.getId_ctlg_tipos_transacion());

		
		return encabezado_comprobante.save();
	}


}

