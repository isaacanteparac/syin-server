package com.example.usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import com.example.perfil.Perfil;
import com.example.ctlg_estados.Ctlg_estados;
import com.example.organizacion.Organizacion;
import com.example.utilidades.DB;


public class Usuario {
	private Integer id;
	private  String nombres;
	private  String apellidos;
	private String correo;
	private  Perfil perfil;
	private  Integer id_perfiles;
	private Integer id_organizaciones;
	private Organizacion organizacion;
	private Integer id_ctlg_estados;
	private Ctlg_estados ctlg_estados;
	
	
	public Usuario () {

	}

	public  Usuario  (Integer id) throws SQLException {
		DB db=new DB();
		PreparedStatement pst = db.prepare("select * from usuario where id=?");
		pst.setInt(1,id);
		ResultSet rs=pst.executeQuery();

		if (rs.next()) {
			ResultSetMetaData md = rs.getMetaData();
			int cols = md.getColumnCount();
			HashMap<String,Object> row = new HashMap<String,Object>(cols);

			for(int i=1; i<=cols; ++i){
				row.put(md.getColumnName(i),rs.getObject(i));
			}

			row.put("perfil",new Perfil((Integer)row.get("id_perfiles")));
			row.put("organizacion", new Organizacion((Integer)row.get("id_organizaciones")));
			row.put("ctlg_estados", new Ctlg_estados((Integer)row.get("id_ctlg_estados")));

			


			this.fromHashMap(row);
		} else {
			throw new SQLException("No se encontro nada");
		}
		
		

	}
	

	public static List<Usuario> index () throws SQLException {
		DB db=new DB();
		PreparedStatement pst = db.prepare("select * from usuario where deleted is null ORDER BY nombres");
		ResultSet rs=pst.executeQuery();
		ResultSetMetaData md = rs.getMetaData();
		int cols = md.getColumnCount();
		List<Usuario> list=new ArrayList<Usuario>();
		while (rs.next()){
			HashMap<String,Object> row = new HashMap<String,Object>(cols);
			for(int i=1; i<=cols; ++i){
				row.put(md.getColumnName(i),rs.getObject(i));

			}
			row.put("perfil",new Perfil((Integer)row.get("id_perfiles")));
			row.put("organizacion", new Organizacion((Integer)row.get("id_organizaciones")));
			row.put("ctlg_estados", new Ctlg_estados((Integer)row.get("id_ctlg_estados")));
			

			Usuario usuario=new Usuario ();
			list.add(usuario.fromHashMap(row));
		}
		db.close();
		return list;
	}

	private Usuario fromHashMap ( HashMap<String,Object> usuario) {
		this.id=(Integer)usuario.get("id");
		this.nombres=(String)usuario.get("nombres");
		this.apellidos=(String)usuario.get("apellidos");
		this.correo=(String)usuario.get("correo");
		this.perfil=(Perfil)usuario.get("perfil");
		this.organizacion=(Organizacion)usuario.get("organizacion");
		this.ctlg_estados=(Ctlg_estados)usuario.get("ctlg_estados");
		
		
		return this;
	}



	private Usuario create() throws SQLException  {

		DB db=new DB();
		try {
			db.getConnection().setAutoCommit(false);
			PreparedStatement pst = db.prepare("INSERT INTO usuario(nombres,apellidos,correo,id_organizaciones,id_ctlg_estados,id_perfiles) values(?,?,?,?,?,?)");
			pst.setString (1, this.nombres);
			pst.setString (2, this.apellidos);
			pst.setString (3, this.correo);
			pst.setInt (4, this.id_organizaciones);
			pst.setInt(5,this.id_ctlg_estados);
			pst.setInt (6, this.id_perfiles);
			

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

	private Usuario update() throws SQLException {
		DB db=new DB();
		PreparedStatement pst =db.prepare("UPDATE usuario set nombres=?,apellidos=?,correo=?,id_organizaciones=?,id_ctlg_estados=?,id_perfiles=? where id=?");
		pst.setString (1, this.nombres);
		pst.setString (2, this.apellidos);
		pst.setString (3, this.correo);
		pst.setInt(4, this.id_organizaciones);
		pst.setInt(5,this.id_ctlg_estados);
		pst.setInt(6, this.id_perfiles);
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

	private Usuario delete() throws SQLException{
		DB db = new DB();
		PreparedStatement pst = db.prepare("Delete FROM usuario WHERE id=?");
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
	
	
	public Usuario save() throws SQLException {
		if(Objects.isNull(this.id)) {
			return this.create();
		} else {
			return this.delete();
		}

	}

	public Integer getId() {
		return id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	
	
	
	
	
	public Perfil getPerfil() {
		return perfil;
	}
	public Integer getId_perfiles() {
		return id_perfiles;
	}
	public void setId_perfiles(Integer id_perfiles) {
		this.id_perfiles = id_perfiles;
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


}
