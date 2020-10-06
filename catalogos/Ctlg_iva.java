package com.example.catalogos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.math.BigDecimal;


import com.example.utilidades.DB;


public class Ctlg_iva {
	private Integer id;
	private  String nombre;
	private BigDecimal porcentaje;
	
	
	public Ctlg_iva () {

	}

	public  Ctlg_iva  (Integer id) throws SQLException {
		DB db=new DB();
		PreparedStatement pst = db.prepare("select * from ctlg_iva where id=?");
		pst.setInt(1,id);
		ResultSet rs=pst.executeQuery();

		if (rs.next()) {
			ResultSetMetaData md = rs.getMetaData();
			int cols = md.getColumnCount();
			HashMap<String,Object> row = new HashMap<String,Object>(cols);

			for(int i=1; i<=cols; ++i){
				row.put(md.getColumnName(i),rs.getObject(i));
			}

			this.fromHashMap(row);
		} else {
			throw new SQLException("No se encontro nada");
		}
		
		

	}
	

	public static List<Ctlg_iva> index () throws SQLException {
		DB db=new DB();
		PreparedStatement pst = db.prepare("select * from ctlg_iva");
		ResultSet rs=pst.executeQuery();
		ResultSetMetaData md = rs.getMetaData();
		int cols = md.getColumnCount();
		List<Ctlg_iva> list=new ArrayList<Ctlg_iva>();
		while (rs.next()){
			HashMap<String,Object> row = new HashMap<String,Object>(cols);
			for(int i=1; i<=cols; ++i){
				row.put(md.getColumnName(i),rs.getObject(i));

			}
			
			

			Ctlg_iva ctlg_iva=new Ctlg_iva ();
			list.add(ctlg_iva.fromHashMap(row));
		}
		db.close();
		return list;
	}

	private Ctlg_iva fromHashMap ( HashMap<String,Object> ctlg_iva) {
		this.id=(Integer)ctlg_iva.get("id");
		this.nombre=(String)ctlg_iva.get("nombre");
		this.porcentaje=(BigDecimal)ctlg_iva.get("porcentaje");

		
		
		return this;
	}



	private Ctlg_iva create() throws SQLException  {

		DB db=new DB();
		try {
			db.getConnection().setAutoCommit(false);
			PreparedStatement pst = db.prepare("INSERT INTO ctlg_iva(nombre,porcentaje) values(?,?)");
			pst.setString (1, this.nombre);
			pst.setBigDecimal(2, this.porcentaje);

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

	private Ctlg_iva update() throws SQLException {
		DB db=new DB();
		PreparedStatement pst =db.prepare("UPDATE ctlg_iva set nombre=?, porcentaje=? where id=?");
		pst.setString (1, this.nombre);
		pst.setBigDecimal(2, this.porcentaje);
		pst.setInt(3, this.id);
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

	public Ctlg_iva save() throws SQLException {
		if(Objects.isNull(this.id)) {
			return this.create();
		} else {
			return this.update();
		}

	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombres(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}




}
