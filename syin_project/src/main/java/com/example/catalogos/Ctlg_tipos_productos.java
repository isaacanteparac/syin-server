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

public class Ctlg_tipos_productos {
	private Integer id;
	private  String nombre;

	
	public Ctlg_tipos_productos () {

	}

	public  Ctlg_tipos_productos  (Integer id) throws SQLException {
		DB db=new DB();
		PreparedStatement pst = db.prepare("select * from ctlg_tipos_productos where id=?");
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

	public static List<Ctlg_tipos_productos> index () throws SQLException {
		DB db=new DB();
		PreparedStatement pst = db.prepare("select * from ctlg_tipos_productos");
		ResultSet rs=pst.executeQuery();
		ResultSetMetaData md = rs.getMetaData();
		int cols = md.getColumnCount();
		List<Ctlg_tipos_productos> list=new ArrayList<Ctlg_tipos_productos>();
		while (rs.next()){
			HashMap<String,Object> row = new HashMap<String,Object>(cols);
			for(int i=1; i<=cols; ++i){
				row.put(md.getColumnName(i),rs.getObject(i));

			}			
			Ctlg_tipos_productos ctlg_tipos_productos=new Ctlg_tipos_productos ();
			list.add(ctlg_tipos_productos.fromHashMap(row));
		}
		db.close();
		return list;
	}

	private Ctlg_tipos_productos fromHashMap ( HashMap<String,Object> ctlg_tipos_productos) {
		this.id=(Integer)ctlg_tipos_productos.get("id");
		this.nombre=(String)ctlg_tipos_productos.get("nombre");
		return this;
	}



	private Ctlg_tipos_productos create() throws SQLException  {

		DB db=new DB();
		try {
			db.getConnection().setAutoCommit(false);
			PreparedStatement pst = db.prepare("INSERT INTO ctlg_tipos_productos(nombre) values(?)");
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

	private Ctlg_tipos_productos update() throws SQLException {
		DB db=new DB();
		PreparedStatement pst =db.prepare("UPDATE ctlg_tipos_productos set nombre=? where id=?");
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

	public Ctlg_tipos_productos save() throws SQLException {
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
