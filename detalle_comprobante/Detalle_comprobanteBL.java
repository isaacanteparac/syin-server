package com.example.detalle_comprobante;

import java.sql.SQLException;
import java.util.List;

import com.example.utilidades.Helpers;


public class Detalle_comprobanteBL {
	public static List<Detalle_comprobante> index() throws SQLException  {
		return Detalle_comprobante.index();
	}

	public static Detalle_comprobante show(Integer id) throws SQLException  {
		return new Detalle_comprobante(id);
	}


	public static Detalle_comprobante create(String json) throws SQLException {
		Detalle_comprobante req=  (Detalle_comprobante) Helpers.JsonToObject(json, Detalle_comprobante.class);
		Detalle_comprobante detalle_comprobante=new Detalle_comprobante();
		detalle_comprobante.setCantidad(req.getCantidad());
		detalle_comprobante.setPrecio(req.getPrecio());
		detalle_comprobante.setTotal(req.getTotal());
		detalle_comprobante.setId_productos(req.getId_productos());
		detalle_comprobante.setId_provedor(req.getId_provedor());
		detalle_comprobante.setId_encabezado_comprobante(req.getId_encabezado_comprobante());
		detalle_comprobante.setExpiracion(req.getExpiracion());




	
		return detalle_comprobante.save();
	}


	public static Detalle_comprobante update(Integer id,String json) throws SQLException {
		Detalle_comprobante req=  (Detalle_comprobante) Helpers.JsonToObject(json, Detalle_comprobante.class);
		Detalle_comprobante detalle_comprobante=new Detalle_comprobante(id);
		detalle_comprobante.setCantidad(req.getCantidad());
		detalle_comprobante.setPrecio(req.getPrecio());
		detalle_comprobante.setTotal(req.getTotal());
		detalle_comprobante.setId_productos(req.getId_productos());
		detalle_comprobante.setId_provedor(req.getId_provedor());
		detalle_comprobante.setId_encabezado_comprobante(req.getId_encabezado_comprobante());
		detalle_comprobante.setExpiracion(req.getExpiracion());


		
		return detalle_comprobante.save();
	}


}