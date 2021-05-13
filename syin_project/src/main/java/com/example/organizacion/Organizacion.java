package com.example.organizacion;

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

public class Organizacion {
	private Integer id;
	private  String nombre;
	private  String correo;
	private  String telefono;
	private  String ubicacion;
	private Integer id_ctlg_estados;
	private Ctlg_estados ctlg_estados;
	
	public Organizacion () {

	}

	public  Organizacion  (Integer id) throws SQLException {
		DB db=new DB();
		PreparedStatement pst = db.prepare("select * from organizaciones where id=?");
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

	public static List<Organizacion> index () throws SQLException {
		DB db=new DB();
		PreparedStatement pst = db.prepare("select * from organizaciones");
		ResultSet rs=pst.executeQuery();
		ResultSetMetaData md = rs.getMetaData();
		int cols = md.getColumnCount();
		List<Organizacion> list=new ArrayList<Organizacion>();
		while (rs.next()){
			HashMap<String,Object> row = new HashMap<String,Object>(cols);
			for(int i=1; i<=cols; ++i){
				row.put(md.getColumnName(i),rs.getObject(i));

			}
			
			row.put("ctlg_estados", new Ctlg_estados((Integer)row.get("id_ctlg_estados")));
			

			Organizacion organizacion=new Organizacion ();
			list.add(organizacion.fromHashMap(row));
		}
		db.close();
		return list;
	}

	private Organizacion fromHashMap ( HashMap<String,Object> organizacion) {
		this.id=(Integer)organizacion.get("id");
		this.nombre=(String)organizacion.get("nombre");
		this.correo=(String)organizacion.get("correo");
		this.telefono=(String)organizacion.get("telefono");
		this.ubicacion=(String)organizacion.get("ubicacion");
		this.ctlg_estados=(Ctlg_estados)organizacion.get("ctlg_estados");
		
		

		return this;
	}



	private Organizacion create() throws SQLException  {

		DB db=new DB();
		try {
			db.getConnection().setAutoCommit(false);
			PreparedStatement pst = db.prepare("INSERT INTO organizaciones(nombre,correo,telefono,ubicacion,id_ctlg_estados) values(?,?,?,?,?)");
			pst.setString (1, this.nombre);
			pst.setString (2, this.correo);
			pst.setString (3, this.telefono);
			pst.setString (4, this.ubicacion);
			pst.setInt(5,this.id_ctlg_estados);
			

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

	private Organizacion update() throws SQLException {
		DB db=new DB();
		PreparedStatement pst =db.prepare("UPDATE organizaciones set nombre=?,correo=?,telefono=?,ubicacion=?,id_ctlg_estados=? where id=?");
		pst.setString (1, this.nombre);
		pst.setString (2, this.correo);
		pst.setString (3, this.telefono);
		pst.setString (4, this.ubicacion);
		pst.setInt(5,this.id_ctlg_estados);
		pst.setInt(6, this.id);
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
	
	private Organizacion delete() throws SQLException{
		DB db = new DB();
		PreparedStatement pst = db.prepare("Delete FROM organizaciones WHERE id=?");
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
	

	public Organizacion save() throws SQLException {
		if(Objects.isNull(this.id)) {
			return this.create();
		} else {
			return this.delete();
		}

	}

	public Integer getId() {
		return id;
	}

	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	
	public Ctlg_estados getCtlg_estados() {
		return ctlg_estados;
	}
	public Integer getId_ctlg_estados() {
		return id_ctlg_estados;
	}
	public void setId_ctlg_estados(Integer id_ctlg_estados) {
		this.id_ctlg_estados = id_ctlg_estados;
	}

}



