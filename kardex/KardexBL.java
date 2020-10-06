package com.example.kardex;

import java.sql.SQLException;
import java.util.List;

import com.example.kardex.Kardex;
import com.example.utilidades.Helpers;

public class KardexBL {
	public static List<Kardex> index() throws SQLException  {
		return Kardex.index();
	}

	public static Kardex show(Integer id) throws SQLException  {
		return new Kardex(id);
	}


	public static Kardex create(String json) throws SQLException {
		Kardex req=  (Kardex) Helpers.JsonToObject(json, Kardex.class);
		Kardex kardex=new Kardex();
		kardex.setCantidad(req.getCantidad());
		kardex.setPrecio_unitario(req.getPrecio_unitario());
		kardex.setId_detalle_comprobante(req.getId_detalle_comprobante());

	
		return kardex.save();
	}


	public static Kardex update(Integer id,String json) throws SQLException {
		Kardex req=  (Kardex) Helpers.JsonToObject(json, Kardex.class);
		Kardex kardex=new Kardex(id);
		kardex.setCantidad(req.getCantidad());
		kardex.setPrecio_unitario(req.getPrecio_unitario());
		kardex.setId_detalle_comprobante(req.getId_detalle_comprobante());
		
		return kardex.save();
	}

}