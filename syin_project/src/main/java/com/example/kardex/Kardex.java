package com.example.kardex;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.math.BigDecimal;

import com.example.detalle_comprobante.Detalle_comprobante;
import com.example.utilidades.DB;

public class Kardex {
	private Integer id;
	private  BigDecimal cantidad;
	private  BigDecimal precio_unitario;
	private Integer id_detalle_comprobante;
	private Detalle_comprobante detalle_comprobante;
	
	public Kardex () {

	}

	public  Kardex  (Integer id) throws SQLException {
		DB db=new DB();
		PreparedStatement pst = db.prepare("select * from kardex where id=?");
		pst.setInt(1,id);
		ResultSet rs=pst.executeQuery();

		if (rs.next()) {
			ResultSetMetaData md = rs.getMetaData();
			int cols = md.getColumnCount();
			HashMap<String,Object> row = new HashMap<String,Object>(cols);

			for(int i=1; i<=cols; ++i){
				row.put(md.getColumnName(i),rs.getObject(i));
			}

			
			row.put("detalle_comprobante", new Detalle_comprobante((Integer)row.get("id_detalle_comprobante")));

			


			this.fromHashMap(row);
		} else {
			throw new SQLException("No se encontro nada");
		}

	}

	public static List<Kardex> index () throws SQLException {
		DB db=new DB();
		PreparedStatement pst = db.prepare("select * from kardex");
		ResultSet rs=pst.executeQuery();
		ResultSetMetaData md = rs.getMetaData();
		int cols = md.getColumnCount();
		List<Kardex> list=new ArrayList<Kardex>();
		while (rs.next()){
			HashMap<String,Object> row = new HashMap<String,Object>(cols);
			for(int i=1; i<=cols; ++i){
				row.put(md.getColumnName(i),rs.getObject(i));

			}
			
			row.put("detalle_comprobante", new Detalle_comprobante((Integer)row.get("id_detalle_comprobante")));
			

			Kardex kardex=new Kardex ();
			list.add(kardex.fromHashMap(row));
		}
		db.close();
		return list;
	}

	private Kardex fromHashMap ( HashMap<String,Object> kardex) {
		this.id=(Integer)kardex.get("id");
		this.cantidad=(BigDecimal)kardex.get("cantidad");
		this.precio_unitario=(BigDecimal)kardex.get("precio_unitario");
		this.detalle_comprobante=(Detalle_comprobante)kardex.get("detalle_comprobante");
		
		

		return this;
	}



	private Kardex create() throws SQLException  {

		DB db=new DB();
		try {
			db.getConnection().setAutoCommit(false);
			PreparedStatement pst = db.prepare("INSERT INTO kardex(cantidad,precio_unitario,id_detalle_comprobante) values(?,?,?)");
			pst.setBigDecimal (1, this.cantidad);
			pst.setBigDecimal (2, this.precio_unitario);
			pst.setInt(3,this.id_detalle_comprobante);
			

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

	private Kardex update() throws SQLException {
		DB db=new DB();
		PreparedStatement pst =db.prepare("UPDATE kardex set cantidad=?,precio_unitario=?,id_detalle_comprobante=? where id=?");
		pst.setBigDecimal (1, this.cantidad);
		pst.setBigDecimal (2, this.precio_unitario);
		pst.setInt(3,this.id_detalle_comprobante);
		pst.setInt(4, this.id);
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
	
	public Kardex delete() throws SQLException{
		DB db = new DB();
		PreparedStatement pst = db.prepare("Delete FROM kardex WHERE id=?");
		pst.setInt(1,this.id);
		Integer affectedRows = pst.executeUpdate();
		
		if (affectedRows <=0) {
			throw new SQLException("Error al elimimar");
		}
		ResultSet rs = pst.getGeneratedKeys();
		if (rs.next()) {
			this.id=(int) rs.getLong(1);
		}
		db.close();
		return this;
	}
	
	
	public Kardex save() throws SQLException {
		if(Objects.isNull(this.id)) {
			return this.create();
		} else {
			return this.delete();
		}

	}

	public Integer getId() {
		return id;
	}

	
	

	public BigDecimal getCantidad() {
		return cantidad;
	}
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	
	public BigDecimal getPrecio_unitario() {
		return precio_unitario;
	}
	public void setPrecio_unitario(BigDecimal precio_unitario) {
		this.precio_unitario = precio_unitario;
	}
	
	
	public Detalle_comprobante getDetalle_comprobante() {
		return detalle_comprobante;
	}
	public Integer getId_detalle_comprobante() {
		return id_detalle_comprobante;
	}
	public void setId_detalle_comprobante(Integer id_detalle_comprobante) {
		this.id_detalle_comprobante = id_detalle_comprobante;
	}

	

	

}


