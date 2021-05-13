package com.example.encabezado_comprobante;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import com.example.catalogos.Ctlg_tipos_transacion;
import com.example.catalogos.Ctlg_tipos_comprobante;
import com.example.usuario.Usuario;
import com.example.organizacion.Organizacion;
import com.example.utilidades.DB;


public class Encabezado_comprobante {
	private Integer id;
	private  String num_comprovante;
	private Integer id_organizacion;
	private Organizacion organizacion;
	private Integer id_ctlg_tipos_transacion;
	private Ctlg_tipos_transacion ctlg_tipos_transacion;
	private Integer id_usuario;
	private Usuario usuario;
	private Integer id_ctlg_tipos_comprobante;
	private Ctlg_tipos_comprobante ctlg_tipos_comprobante;
	
	public Encabezado_comprobante () {

	}

	public  Encabezado_comprobante  (Integer id) throws SQLException {
		DB db=new DB();
		PreparedStatement pst = db.prepare("select * from encabezado_comprobante where id=?");
		pst.setInt(1,id);
		ResultSet rs=pst.executeQuery();

		if (rs.next()) {
			ResultSetMetaData md = rs.getMetaData();
			int cols = md.getColumnCount();
			HashMap<String,Object> row = new HashMap<String,Object>(cols);

			for(int i=1; i<=cols; ++i){
				row.put(md.getColumnName(i),rs.getObject(i));
			}

			row.put("usuario",new Usuario((Integer)row.get("id_usuario")));
			row.put("organizacion", new Organizacion((Integer)row.get("id_organizacion")));
			row.put("ctlg_tipos_comprobante", new Ctlg_tipos_comprobante((Integer)row.get("id_ctlg_tipos_comprobante")));
			row.put("ctlg_tipos_transacion", new Ctlg_tipos_transacion((Integer)row.get("id_ctlg_tipos_transacion")));

			


			this.fromHashMap(row);
		} else {
			throw new SQLException("No se encontro nada");
		}
		
		

	}
	

	public static List<Encabezado_comprobante> index () throws SQLException {
		DB db=new DB();
		PreparedStatement pst = db.prepare("select * from encabezado_comprobante");
		ResultSet rs=pst.executeQuery();
		ResultSetMetaData md = rs.getMetaData();
		int cols = md.getColumnCount();
		List<Encabezado_comprobante> list=new ArrayList<Encabezado_comprobante>();
		while (rs.next()){
			HashMap<String,Object> row = new HashMap<String,Object>(cols);
			for(int i=1; i<=cols; ++i){
				row.put(md.getColumnName(i),rs.getObject(i));

			}
			row.put("usuario",new Usuario((Integer)row.get("id_usuario")));
			row.put("organizacion", new Organizacion((Integer)row.get("id_organizacion")));
			row.put("ctlg_tipos_comprobante", new Ctlg_tipos_comprobante((Integer)row.get("id_ctlg_tipos_comprobante")));
			row.put("ctlg_tipos_transacion", new Ctlg_tipos_transacion((Integer)row.get("id_ctlg_tipos_transacion")));

			

			Encabezado_comprobante encabezado_comprobante=new Encabezado_comprobante ();
			list.add(encabezado_comprobante.fromHashMap(row));
		}
		db.close();
		return list;
	}

	private Encabezado_comprobante fromHashMap ( HashMap<String,Object> encabezado_comprobante) {
		this.id=(Integer)encabezado_comprobante.get("id");
		this.num_comprovante=(String)encabezado_comprobante.get("num_comprovante");
		this.organizacion=(Organizacion)encabezado_comprobante.get("organizacion");
		this.ctlg_tipos_comprobante=(Ctlg_tipos_comprobante)encabezado_comprobante.get("ctlg_tipos_comprobante");
		this.ctlg_tipos_transacion=(Ctlg_tipos_transacion)encabezado_comprobante.get("ctlg_tipos_transacion");

		
		return this;
	}



	private Encabezado_comprobante create() throws SQLException  {

		DB db=new DB();
		try {
			db.getConnection().setAutoCommit(false);
			PreparedStatement pst = db.prepare("INSERT INTO encabezado_comprobante(num_comprovante,id_organizacion,id_usuario,id_ctlg_tipos_comprobante,id_ctlg_tipos_transacion) values(?,?,?,?,?)");
			pst.setString (1, this.num_comprovante);
			pst.setInt (2, this.id_organizacion);
			pst.setInt(3,this.id_usuario);
			pst.setInt (4, this.id_ctlg_tipos_comprobante);
			pst.setInt (5, this.id_ctlg_tipos_transacion);

			

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

	private Encabezado_comprobante update() throws SQLException {
		DB db=new DB();
		PreparedStatement pst =db.prepare("UPDATE encabezado_comprobante set num_comprovante=?,id_organizacion=?,id_usuario=?,id_ctlg_tipos_comprobante=?,id_ctlg_tipos_transacion=? where id=?");
		pst.setString (1, this.num_comprovante);
		pst.setInt(2, this.id_organizacion);
		pst.setInt(3, this.id_usuario);
		pst.setInt(4,this.id_ctlg_tipos_comprobante);
		pst.setInt(5,this.id_ctlg_tipos_transacion);
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

	public Encabezado_comprobante save() throws SQLException {
		if(Objects.isNull(this.id)) {
			return this.create();
		} else {
			return this.update();
		}

	}

	public Integer getId() {
		return id;
	}

	
	public String getNum_comprovante() {
		return num_comprovante;
	}

	public void setNum_comprovante(String num_comprovante) {
		this.num_comprovante = num_comprovante;
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


	public Ctlg_tipos_comprobante getCtlg_tipos_comprobante() {
		return ctlg_tipos_comprobante;
	}
	public Integer getId_ctlg_tipos_comprobante() {
		return id_ctlg_tipos_comprobante;
	}
	public void setId_ctlg_tipos_comprobante(Integer id_ctlg_tipos_comprobante) {
		this.id_ctlg_tipos_comprobante = id_ctlg_tipos_comprobante;
	}
	
	
	public Ctlg_tipos_transacion getCtlg_tipos_transacion() {
		return ctlg_tipos_transacion;
	}
	public Integer getId_ctlg_tipos_transacion() {
		return id_ctlg_tipos_transacion;
	}
	public void setId_ctlg_tipos_transacion(Integer id_ctlg_tipos_transacion) {
		this.id_ctlg_tipos_transacion = id_ctlg_tipos_transacion;
	}

	
	public Usuario getUsuario() {
		return usuario;
	}
	public Integer getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	

}
