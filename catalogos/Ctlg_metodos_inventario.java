package com.example.catalogos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import com.example.ctlg_estados.Ctlg_estados;
import com.example.utilidades.DB;


public class Ctlg_metodos_inventario {
	private Integer id;
	private  String nombre;
	private Integer id_ctlg_estados;
	private Ctlg_estados ctlg_estados;
	
	public Ctlg_metodos_inventario () {

	}

	public  Ctlg_metodos_inventario  (Integer id) throws SQLException {
		DB db=new DB();
		PreparedStatement pst = db.prepare("select * from ctlg_metodos_inventario where id=?");
		pst.setInt(1,id);
		ResultSet rs=pst.executeQuery();

		if (rs.next()) {
			ResultSetMetaData md = rs.getMetaData();
			int cols = md.getColumnCount();
			HashMap<String,Object> row = new HashMap<String,Object>(cols);

			for(int i=1; i<=cols; ++i){
				row.put(md.getColumnName(i),rs.getObject(i));
			}

			
			row.put("ctlg_estados", new Ctlg_estados((Integer)row.get("id_ctlg_estados")));

			


			this.fromHashMap(row);
		} else {
			throw new SQLException("No se encontro nada");
		}
		
		

	}
	

	public static List<Ctlg_metodos_inventario> index () throws SQLException {
		DB db=new DB();
		PreparedStatement pst = db.prepare("select * from ctlg_metodos_inventario");
		ResultSet rs=pst.executeQuery();
		ResultSetMetaData md = rs.getMetaData();
		int cols = md.getColumnCount();
		List<Ctlg_metodos_inventario> list=new ArrayList<Ctlg_metodos_inventario>();
		while (rs.next()){
			HashMap<String,Object> row = new HashMap<String,Object>(cols);
			for(int i=1; i<=cols; ++i){
				row.put(md.getColumnName(i),rs.getObject(i));

			}
			
			row.put("ctlg_estados", new Ctlg_estados((Integer)row.get("id_ctlg_estados")));
			

			Ctlg_metodos_inventario ctlg_metodos_inventario=new Ctlg_metodos_inventario ();
			list.add(ctlg_metodos_inventario.fromHashMap(row));
		}
		db.close();
		return list;
	}

	private Ctlg_metodos_inventario fromHashMap ( HashMap<String,Object> ctlg_metodos_inventario) {
		this.id=(Integer)ctlg_metodos_inventario.get("id");
		this.nombre=(String)ctlg_metodos_inventario.get("nombre");
		this.ctlg_estados=(Ctlg_estados)ctlg_metodos_inventario.get("ctlg_estados");
		
		
		return this;
	}



	private Ctlg_metodos_inventario create() throws SQLException  {

		DB db=new DB();
		try {
			db.getConnection().setAutoCommit(false);
			PreparedStatement pst = db.prepare("INSERT INTO ctlg_metodos_inventario(nombre,id_ctlg_estados) values(?,?)");
			pst.setString (1, this.nombre);
			pst.setInt(2,this.id_ctlg_estados);
			

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

	private Ctlg_metodos_inventario update() throws SQLException {
		DB db=new DB();
		PreparedStatement pst =db.prepare("UPDATE ctlg_metodos_inventario set nombre=?,id_ctlg_estados=? where id=?");
		pst.setString (1, this.nombre);
		pst.setInt(2,this.id_ctlg_estados);
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

	public Ctlg_metodos_inventario save() throws SQLException {
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

	
	public Integer getId_ctlg_estados() {
		return id_ctlg_estados;
	}

	public void setId_ctlg_estados(Integer id_ctlg_estados) {
		this.id_ctlg_estados = id_ctlg_estados;
	}

	public Ctlg_estados getCtlg_estados() {
		return ctlg_estados;
	}

	public void setCtlg_estados(Ctlg_estados ctlg_estados) {
		this.ctlg_estados = ctlg_estados;
	}




}
