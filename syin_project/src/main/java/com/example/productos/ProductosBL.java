package com.example.productos;

import java.sql.SQLException;
import java.util.List;

import com.example.utilidades.Helpers;


public class ProductosBL {
	public static List<Productos> index() throws SQLException  {
		return Productos.index();
	}

	public static Productos show(Integer id) throws SQLException  {
		return new Productos(id);
	}


	public static Productos create(String json) throws SQLException {
		Productos req=  (Productos) Helpers.JsonToObject(json, Productos.class);
		Productos productos=new Productos();
		productos.setNombre(req.getNombre());
		productos.setCantidad_min(req.getCantidad_min());
		productos.setCantidad_maxima(req.getCantidad_maxima());
		productos.setDescripcion(req.getDescripcion());
		productos.setImagen(req.getImagen());
		productos.setCodigo(req.getCodigo());
		productos.setId_organizaciones(req.getId_organizaciones());
		productos.setId_ctlg_estados(req.getId_ctlg_estados());
		productos.setId_ctlg_metodos_inventario(req.getId_ctlg_metodos_inventario());
		productos.setId_ctlg_unidades_medidas(req.getId_ctlg_unidades_medidas());
		productos.setId_ctlg_tipos_productos(req.getId_ctlg_tipos_productos());
		productos.setId_ctlg_iva(req.getId_ctlg_iva());





	
		return productos.save();
	}


	public static Productos update(Integer id,String json) throws SQLException {
		Productos req=  (Productos) Helpers.JsonToObject(json, Productos.class);
		Productos productos=new Productos(id);
		productos.setNombre(req.getNombre());
		productos.setCantidad_min(req.getCantidad_min());
		productos.setCantidad_maxima(req.getCantidad_maxima());
		productos.setDescripcion(req.getDescripcion());
		productos.setImagen(req.getImagen());
		productos.setCodigo(req.getCodigo());
		productos.setId_organizaciones(req.getId_organizaciones());
		productos.setId_ctlg_estados(req.getId_ctlg_estados());
		productos.setId_ctlg_metodos_inventario(req.getId_ctlg_metodos_inventario());
		productos.setId_ctlg_unidades_medidas(req.getId_ctlg_unidades_medidas());
		productos.setId_ctlg_tipos_productos(req.getId_ctlg_tipos_productos());
		productos.setId_ctlg_iva(req.getId_ctlg_iva());

		
		
		
		return productos.save();
	}

	
	
	public static Productos delete(Integer id) throws SQLException{
		Productos  productos = new 	Productos(id);
		return productos.save();
	}


}
