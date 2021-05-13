package com.example.provedor;

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

public class Provedor {
	private Integer id;
	private  String nombre;
	private  String telefono;
	private String correo;
	private String  ubicacion;
	private Integer id_organizacion;
	private Organizacion organizacion;
	private Integer id_ctlg_estados;
	private Ctlg_estados ctlg_estados;
	
	public Provedor () {

	}

	public  Provedor  (Integer id) throws SQLException {
		DB db=new DB();
		PreparedStatement pst = db.prepare("select * from provedor where id=?");
		pst.setInt(1,id);
		ResultSet rs=pst.executeQuery();

		if (rs.next()) {
			ResultSetMetaData md = rs.getMetaData();
			int cols = md.getColumnCount();
			HashMap<String,Object> row = new HashMap<String,Object>(cols);

			for(int i=1; i<=cols; ++i){
				row.put(md.getColumnName(i),rs.getObject(i));
			}


			row.put("organizacion", new Organizacion((Integer)row.get("id_organizacion")));
			row.put("ctlg_estados", new Ctlg_estados((Integer)row.get("id_ctlg_estados")));

			


			this.fromHashMap(row);
		} else {
			throw new SQLException("No se encontro nada");
		}
		
		

	}
	

	public static List<Provedor> index () throws SQLException {
		DB db=new DB();
		PreparedStatement pst = db.prepare("select * from provedor");
		ResultSet rs=pst.executeQuery();
		ResultSetMetaData md = rs.getMetaData();
		int cols = md.getColumnCount();
		List<Provedor> list=new ArrayList<Provedor>();
		while (rs.next()){
			HashMap<String,Object> row = new HashMap<String,Object>(cols);
			for(int i=1; i<=cols; ++i){
				row.put(md.getColumnName(i),rs.getObject(i));

			}
			
			row.put("organizacion", new Organizacion((Integer)row.get("id_organizacion")));
			row.put("ctlg_estados", new Ctlg_estados((Integer)row.get("id_ctlg_estados")));
			

			Provedor provedor=new Provedor ();
			list.add(provedor.fromHashMap(row));
		}
		db.close();
		return list;
	}

	private Provedor fromHashMap ( HashMap<String,Object> provedor) {
		this.id=(Integer)provedor.get("id");
		this.nombre=(String)provedor.get("nombre");
		this.telefono=(String)provedor.get("telefono");
		this.correo=(String)provedor.get("correo");
		this.ubicacion=(String)provedor.get("ubicacion");
		this.organizacion=(Organizacion)provedor.get("organizacion");
		this.ctlg_estados=(Ctlg_estados)provedor.get("ctlg_estados");
		
		
		return this;
	}



	private Provedor create() throws SQLException  {

		DB db=new DB();
		try {
			db.getConnection().setAutoCommit(false);
			PreparedStatement pst = db.prepare("INSERT INTO provedor(nombre,telefono,correo,ubicacion,id_organizacion,id_ctlg_estados) values(?,?,?,?,?,?)");
			pst.setString (1, this.nombre);
			pst.setString (2, this.telefono);
			pst.setString (3, this.correo);
			pst.setString (4, this.ubicacion);
			pst.setInt (5, this.id_organizacion);
			pst.setInt(6,this.id_ctlg_estados);
			

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

	private Provedor update() throws SQLException {
		DB db=new DB();
		PreparedStatement pst =db.prepare("UPDATE provedor set nombre=?,telefono=?,correo=?,ubicacion=?,id_organizacion=?,id_ctlg_estados=? where id=?");
		pst.setString (1, this.nombre);
		pst.setString (2, this.telefono);
		pst.setString (3, this.correo);
		pst.setString (4, this.ubicacion);
		pst.setInt(5, this.id_organizacion);
		pst.setInt(6,this.id_ctlg_estados);
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

	private Provedor delete() throws SQLException{
		DB db = new DB();
		PreparedStatement pst = db.prepare("Delete FROM provedor WHERE id=?");
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
	
	
	public Provedor save() throws SQLException {
		if(Objects.isNull(this.id)) {
			return this.create();
		} else {
			return this.delete();
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	
	public Organizacion getOrganizacion() {
		return organizacion;
	}
	public Integer getId_organizacion() {
		return id_organizacion;
	}
	public void setId_organizacion(Integer id_organizacion) {
		this.id_organizacion = id_organizacion;
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

	

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	

}
