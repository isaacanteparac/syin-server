package com.example.productos_provedores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import com.example.productos.Productos;
import com.example.provedor.Provedor;
import com.example.ctlg_estados.Ctlg_estados;
import com.example.organizacion.Organizacion;
import com.example.utilidades.DB;


public class Productos_provedores {
	private Integer id;
	private Integer id_organizaciones;
	private Organizacion organizacion;
	private Integer id_ctlg_estados;
	private Ctlg_estados ctlg_estados;
	private Integer id_provedor;
	private Provedor provedor;
	private Integer id_productos;
	private Productos productos;
	
	
	
	public Productos_provedores () {

	}

	public  Productos_provedores  (Integer id) throws SQLException {
		DB db=new DB();
		PreparedStatement pst = db.prepare("select * from productos_provedores where id=?");
		pst.setInt(1,id);
		ResultSet rs=pst.executeQuery();

		if (rs.next()) {
			ResultSetMetaData md = rs.getMetaData();
			int cols = md.getColumnCount();
			HashMap<String,Object> row = new HashMap<String,Object>(cols);

			for(int i=1; i<=cols; ++i){
				row.put(md.getColumnName(i),rs.getObject(i));
			}
			
			row.put("productos", new Productos((Integer)row.get("id_productos")));
			row.put("provedor", new Provedor((Integer)row.get("id_provedor")));
			row.put("organizacion", new Organizacion((Integer)row.get("id_organizaciones")));
			row.put("ctlg_estados", new Ctlg_estados((Integer)row.get("id_ctlg_estados")));

			


			this.fromHashMap(row);
		} else {
			throw new SQLException("No se encontro nada");
		}
		
		

	}
	

	public static List<Productos_provedores> index () throws SQLException {
		DB db=new DB();
		PreparedStatement pst = db.prepare("select * from productos_provedores");
		ResultSet rs=pst.executeQuery();
		ResultSetMetaData md = rs.getMetaData();
		int cols = md.getColumnCount();
		List<Productos_provedores> list=new ArrayList<Productos_provedores>();
		while (rs.next()){
			HashMap<String,Object> row = new HashMap<String,Object>(cols);
			for(int i=1; i<=cols; ++i){
				row.put(md.getColumnName(i),rs.getObject(i));

			}
			
			row.put("productos", new Productos((Integer)row.get("id_productos")));
			row.put("provedor", new Provedor((Integer)row.get("id_provedor")));
			row.put("organizacion", new Organizacion((Integer)row.get("id_organizaciones")));
			row.put("ctlg_estados", new Ctlg_estados((Integer)row.get("id_ctlg_estados")));
			

			Productos_provedores productos_provedores=new Productos_provedores ();
			list.add(productos_provedores.fromHashMap(row));
		}
		db.close();
		return list;
	}

	private Productos_provedores fromHashMap ( HashMap<String,Object> productos_provedores) {
		this.id=(Integer)productos_provedores.get("id");
		this.organizacion=(Organizacion)productos_provedores.get("organizacion");
		this.ctlg_estados=(Ctlg_estados)productos_provedores.get("ctlg_estados");
		this.productos=(Productos)productos_provedores.get("productos");
		this.provedor=(Provedor)productos_provedores.get("provedor");


		
		
		return this;
	}



	private Productos_provedores create() throws SQLException  {

		DB db=new DB();
		try {
			db.getConnection().setAutoCommit(false);
			PreparedStatement pst = db.prepare("INSERT INTO productos_provedores(id_organizaciones,id_ctlg_estados,id_productos,id_provedor) values(?,?,?,?)");
			pst.setInt (1, this.id_organizaciones);
			pst.setInt(2,this.id_ctlg_estados);
			pst.setInt(3,this.id_productos);
			pst.setInt(4,this.id_provedor);
		




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

	private Productos_provedores update() throws SQLException {
		DB db=new DB();
		PreparedStatement pst =db.prepare("UPDATE productos_provedores set id_organizaciones=?,id_ctlg_estados=?,id_productos=?,id_provedor=? where id=?");
		pst.setInt(1, this.id_organizaciones);
		pst.setInt(2,this.id_ctlg_estados);
		pst.setInt(3,this.id_productos);
		pst.setInt(4,this.id_provedor);
		pst.setInt(5, this.id);
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

	public Productos_provedores save() throws SQLException {
		if(Objects.isNull(this.id)) {
			return this.create();
		} else {
			return this.update();
		}

	}

	public Integer getId() {
		return id;
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

	
	
	public Ctlg_estados getCtlg_estados() {
		return ctlg_estados;
	}
	public Integer getId_ctlg_estados() {
		return id_ctlg_estados;
	}
	public void setId_ctlg_estados(Integer id_ctlg_estados) {
		this.id_ctlg_estados = id_ctlg_estados;
	}
	
	
	public Productos getProductos() {
		return productos;
	}
	public Integer getId_productos() {
		return id_productos;
	}
	public void setId_productos(Integer id_productos) {
		this.id_productos = id_productos;
	}

	
	public Provedor getProvedor() {
		return provedor;
	}
	public Integer getId_provedor() {
		return id_provedor;
	}
	public void setId_provedor(Integer id_provedor) {
		this.id_provedor = id_provedor;
	}


	
}	
	
	



