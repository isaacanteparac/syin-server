package com.example.perfil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import com.example.ctlg_estados.Ctlg_estados;
import com.example.organizacion.Organizacion;

import com.example.utilidades.DB;

public class Perfil {
	private Integer id;
	private  String nombre;
	private Integer id_organizaciones;
	private Organizacion organizacion;
	private Integer id_ctlg_estados;
	private Ctlg_estados ctlg_estados;
	
	
	public Perfil () {

	}
	public  Perfil  (Integer id) throws SQLException {
		DB db=new DB();
		PreparedStatement pst = db.prepare("select * from perfiles where id=?");
		pst.setInt(1,id);
		ResultSet rs=pst.executeQuery();

		if (rs.next()) {
			ResultSetMetaData md = rs.getMetaData();
			int cols = md.getColumnCount();
			HashMap<String,Object> row = new HashMap<String,Object>(cols);

			for(int i=1; i<=cols; ++i){
				row.put(md.getColumnName(i),rs.getObject(i));
			}

			row.put("organizacion", new Organizacion((Integer)row.get("id_organizaciones")));
			row.put("ctlg_estados", new Ctlg_estados((Integer)row.get("id_ctlg_estados")));

			


			this.fromHashMap(row);
		} else {
			throw new SQLException("No se encontro nada");
		}

	}

	public static List<Perfil> index () throws SQLException {
		DB db=new DB();
		PreparedStatement pst = db.prepare("select * from perfiles");
		ResultSet rs=pst.executeQuery();
		ResultSetMetaData md = rs.getMetaData();
		int cols = md.getColumnCount();
		List<Perfil> list=new ArrayList<Perfil>();
		while (rs.next()){
			HashMap<String,Object> row = new HashMap<String,Object>(cols);
			for(int i=1; i<=cols; ++i){
				row.put(md.getColumnName(i),rs.getObject(i));

			}
			row.put("organizacion", new Organizacion((Integer)row.get("id_organizaciones")));
			row.put("ctlg_estados", new Ctlg_estados((Integer)row.get("id_ctlg_estados")));
			

			Perfil perfil=new Perfil ();
			list.add(perfil.fromHashMap(row));
		}
		db.close();
		return list;
	}

	private Perfil fromHashMap ( HashMap<String,Object> perfil) {
		this.id=(Integer)perfil.get("id");
		this.nombre=(String)perfil.get("nombre");
		this.organizacion=(Organizacion)perfil.get("organizacion");
		this.ctlg_estados=(Ctlg_estados)perfil.get("ctlg_estados");
		
		
		return this;
	}



	private Perfil create() throws SQLException  {

		DB db=new DB();
		try {
			db.getConnection().setAutoCommit(false);
			PreparedStatement pst = db.prepare("INSERT INTO perfiles(nombre,id_organizaciones,id_ctlg_estados) values(?,?,?)");
			pst.setString (1, this.nombre);
			pst.setInt (2, this.id_organizaciones);
			pst.setInt(3,this.id_ctlg_estados);
			

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

	private Perfil update() throws SQLException {
		DB db=new DB();
		PreparedStatement pst =db.prepare("UPDATE perfiles set nombre=?,id_organizaciones=?,id_ctlg_estados=?  where id=?");
		pst.setString (1, this.nombre);
		pst.setInt(2, this.id_organizaciones);
		pst.setInt(3,this.id_ctlg_estados);
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

	public Perfil save() throws SQLException {
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


	
	public Organizacion getOrganizacion() {
		return organizacion;
	}
	public Integer getId_organizaciones() {
		return id_organizaciones;
	}
	public void setId_organizaciones(Integer id_organizaciones) {
		this.id_organizaciones = id_organizaciones;
	}
	
	
	public Ctlg_estados getCtlg_estado() {
		return ctlg_estados;
	}
	public Integer getId_ctlg_estados() {
		return id_ctlg_estados;
	}
	public void setId_ctlg_estados(Integer id_ctlg_estados) {
		this.id_ctlg_estados = id_ctlg_estados;
	}

}
