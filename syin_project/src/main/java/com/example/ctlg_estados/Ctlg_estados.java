package com.example.ctlg_estados;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import com.example.utilidades.DB;

public class Ctlg_estados {
	private Integer id;
	private  String nombre;

	
	public Ctlg_estados () {

	}

	public  Ctlg_estados  (Integer id) throws SQLException {
		DB db=new DB();
		PreparedStatement pst = db.prepare("select * from ctlg_estados where id=?");
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

	public static List<Ctlg_estados> index () throws SQLException {
		DB db=new DB();
		PreparedStatement pst = db.prepare("select * from ctlg_estados");
		ResultSet rs=pst.executeQuery();
		ResultSetMetaData md = rs.getMetaData();
		int cols = md.getColumnCount();
		List<Ctlg_estados> list=new ArrayList<Ctlg_estados>();
		while (rs.next()){
			HashMap<String,Object> row = new HashMap<String,Object>(cols);
			for(int i=1; i<=cols; ++i){
				row.put(md.getColumnName(i),rs.getObject(i));

			}			
			Ctlg_estados ctlg_estados=new Ctlg_estados ();
			list.add(ctlg_estados.fromHashMap(row));
		}
		db.close();
		return list;
	}

	private Ctlg_estados fromHashMap ( HashMap<String,Object> ctlg_estados) {
		this.id=(Integer)ctlg_estados.get("id");
		this.nombre=(String)ctlg_estados.get("nombre");
		return this;
	}



	private Ctlg_estados create() throws SQLException  {

		DB db=new DB();
		try {
			db.getConnection().setAutoCommit(false);
			PreparedStatement pst = db.prepare("INSERT INTO ctlg_estados(nombre) values(?)");
			pst.setString (1, this.nombre);
			
			
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

	private Ctlg_estados update() throws SQLException {
		DB db=new DB();
		PreparedStatement pst =db.prepare("UPDATE ctlg_estados set nombre=? where id=?");
		pst.setString (1, this.nombre);
		pst.setInt(2, this.id);
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

	public Ctlg_estados save() throws SQLException {
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

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
