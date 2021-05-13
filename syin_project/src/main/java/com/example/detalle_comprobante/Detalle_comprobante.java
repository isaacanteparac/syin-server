package com.example.detalle_comprobante;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import com.example.encabezado_comprobante.Encabezado_comprobante;
import com.example.provedor.Provedor;
import com.example.productos.Productos;
import com.example.utilidades.DB;
import java.math.BigDecimal;



public class Detalle_comprobante {
	private Integer id;
	private Integer cantidad;
	private BigDecimal precio;
	private BigDecimal total;
	private Integer id_productos;
	private Productos productos;
	private Integer id_provedor;
	private Provedor provedor;
	private Integer id_encabezado_comprobante;
	private Encabezado_comprobante encabezado_comprobante;
	
	
	public Detalle_comprobante () {

	}

	public  Detalle_comprobante  (Integer id) throws SQLException {
		DB db=new DB();
		PreparedStatement pst = db.prepare("select * from detalle_comprobante where id=?");
		pst.setInt(1,id);
		ResultSet rs=pst.executeQuery();

		if (rs.next()) {
			ResultSetMetaData md = rs.getMetaData();
			int cols = md.getColumnCount();
			HashMap<String,Object> row = new HashMap<String,Object>(cols);

			for(int i=1; i<=cols; ++i){
				row.put(md.getColumnName(i),rs.getObject(i));
			}

			row.put("productos",new Productos((Integer)row.get("id_productos")));
			row.put("provedor", new Provedor((Integer)row.get("id_provedor")));
			row.put("encabezado_comprobante", new Encabezado_comprobante((Integer)row.get("id_encabezado_comprobante")));

			


			this.fromHashMap(row);
		} else {
			throw new SQLException("No se encontro nada");
		}
		
		

	}
	

	public static List<Detalle_comprobante> index () throws SQLException {
		DB db=new DB();
		PreparedStatement pst = db.prepare("select * from detalle_comprobante");
		ResultSet rs=pst.executeQuery();
		ResultSetMetaData md = rs.getMetaData();
		int cols = md.getColumnCount();
		List<Detalle_comprobante> list=new ArrayList<Detalle_comprobante>();
		while (rs.next()){
			HashMap<String,Object> row = new HashMap<String,Object>(cols);
			for(int i=1; i<=cols; ++i){
				row.put(md.getColumnName(i),rs.getObject(i));

			}
			row.put("productos",new Productos((Integer)row.get("id_productos")));
			row.put("provedor", new Provedor((Integer)row.get("id_provedor")));
			row.put("encabezado_comprobante", new Encabezado_comprobante((Integer)row.get("id_encabezado_comprobante")));

			

			Detalle_comprobante detalle_comprobante=new Detalle_comprobante ();
			list.add(detalle_comprobante.fromHashMap(row));
		}
		db.close();
		return list;
	}

	private Detalle_comprobante fromHashMap ( HashMap<String,Object> detalle_comprobante) {
		this.id=(Integer)detalle_comprobante.get("id");
		this.cantidad=(Integer)detalle_comprobante.get("cantidad");
		this.precio=(BigDecimal)detalle_comprobante.get("precio");
		this.total=(BigDecimal)detalle_comprobante.get("total");
		this.productos=(Productos)detalle_comprobante.get("productos");
		this.provedor=(Provedor)detalle_comprobante.get("provedor");
		this.encabezado_comprobante=(Encabezado_comprobante)detalle_comprobante.get("encabezado_comprobante");

		
		return this;
	}



	private Detalle_comprobante create() throws SQLException  {

		DB db=new DB();
		try {
			db.getConnection().setAutoCommit(false);
			PreparedStatement pst = db.prepare("INSERT INTO detalle_comprobante(cantidad,precio,total,id_productos,id_provedor,id_encabezado_comprobante) values(?,?,?,?,?,?)");
			pst.setInt (1, this.cantidad);
			pst.setBigDecimal(2, this.precio);
			pst.setBigDecimal(3, this.total);
			pst.setInt (4, this.id_productos);
			pst.setInt(5,this.id_provedor);
			pst.setInt (6, this.id_encabezado_comprobante);

			

			Integer affectedRows=pst.executeUpdate();

			if (affectedRows <= 0) {
				throw new SQLException("Error al guardar");
			}

			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				this.id=(int) rs.getLong(1);
			}


			

			db.getConnection().commit();

			return this;
		}catch(Exception e) {
			db.getConnection().rollback();
			throw new SQLException(e.getMessage());
		}finally {
			db.getConnection().setAutoCommit(true);
			db.close();
		}


	}

	private Detalle_comprobante update() throws SQLException {
		DB db=new DB();
		PreparedStatement pst =db.prepare("UPDATE detalle_comprobante set cantidad=?,precio=?,total=?,id_productos=?,id_provedor=?,id_encabezado_comprobante=? where id=?");
		pst.setInt (1, this.cantidad);
		pst.setBigDecimal(2, this.precio);
		pst.setBigDecimal(3, this.total);
		pst.setInt(4, this.id_productos);
		pst.setInt(5, this.id_provedor);
		pst.setInt(6,this.id_encabezado_comprobante);
		pst.setInt(7, this.id);
		Integer affectedRows=pst.executeUpdate();

		if (affectedRows <= 0) {
			throw new SQLException("Error al actualizar");
		}

		ResultSet rs = pst.getGeneratedKeys();
		if (rs.next()) {
			this.id=(int) rs.getLong(1);
		}
		db.close();
		return this;
	}

	public Detalle_comprobante save() throws SQLException {
		if(Objects.isNull(this.id)) {
			return this.create();
		} else {
			return this.update();
		}

	}

	public Integer getId() {
		return id;
	}

	
	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	
	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	
	
	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	
	
	public Productos getProductos() {
		return productos;
	}
	public Integer getId_productos() {
		return id_productos;
	}
	public void setId_productos(Integer id_productos) {
		this.id_productos = id_productos;
	}


	public Provedor getProvedor() {
		return provedor;
	}
	public Integer getId_provedor() {
		return id_provedor;
	}
	public void setId_provedor(Integer id_provedor) {
		this.id_provedor = id_provedor;
	}
	
	
	public Encabezado_comprobante getEncabezado_comprobante() {
		return encabezado_comprobante;
	}
	public Integer getId_encabezado_comprobante() {
		return id_encabezado_comprobante;
	}
	public void setId_encabezado_comprobante(Integer id_encabezado_comprobante) {
		this.id_encabezado_comprobante = id_encabezado_comprobante;
	}

	

}

