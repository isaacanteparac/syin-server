package com.example.catalogos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


import com.example.utilidades.DB;


public class Ctlg_unidades_medidas {
	private Integer id;
	private  String nombre;
	private  String simbolo;
	
	
	public Ctlg_unidades_medidas () {

	}

	public  Ctlg_unidades_medidas  (Integer id) throws SQLException {
		DB db=new DB();
		PreparedStatement pst = db.prepare("select * from ctlg_unidades_medidas where id=?");
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
	

	public static List<Ctlg_unidades_medidas> index () throws SQLException {
		DB db=new DB();
		PreparedStatement pst = db.prepare("select * from ctlg_unidades_medidas");
		ResultSet rs=pst.executeQuery();
		ResultSetMetaData md = rs.getMetaData();
		int cols = md.getColumnCount();
		List<Ctlg_unidades_medidas> list=new ArrayList<Ctlg_unidades_medidas>();
		while (rs.next()){
			HashMap<String,Object> row = new HashMap<String,Object>(cols);
			for(int i=1; i<=cols; ++i){
				row.put(md.getColumnName(i),rs.getObject(i));

			}
			
			

			Ctlg_unidades_medidas ctlg_unidades_medidas=new Ctlg_unidades_medidas ();
			list.add(ctlg_unidades_medidas.fromHashMap(row));
		}
		db.close();
		return list;
	}

	private Ctlg_unidades_medidas fromHashMap ( HashMap<String,Object> ctlg_unidades_medidas) {
		this.id=(Integer)ctlg_unidades_medidas.get("id");
		this.nombre=(String)ctlg_unidades_medidas.get("nombre");
		this.simbolo=(String)ctlg_unidades_medidas.get("simbolo");

		
		
		return this;
	}



	private Ctlg_unidades_medidas create() throws SQLException  {

		DB db=new DB();
		try {
			db.getConnection().setAutoCommit(false);
			PreparedStatement pst = db.prepare("INSERT INTO ctlg_unidades_medidas(nombre,simobolo) values(?,?)");
			pst.setString (1, this.nombre);
			pst.setString(2, this.simbolo);

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

	private Ctlg_unidades_medidas update() throws SQLException {
		DB db=new DB();
		PreparedStatement pst =db.prepare("UPDATE ctlg_unidades_medidas set nombre=?, simbolo=? where id=?");
		pst.setString (1, this.nombre);
		pst.setString(2, this.simbolo);
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

	public Ctlg_unidades_medidas save() throws SQLException {
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


	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}




}
