package com.example.productos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.math.BigDecimal;

import com.example.catalogos.Ctlg_tipos_productos;
import com.example.catalogos.Ctlg_iva;
import com.example.catalogos.Ctlg_unidades_medidas;
import com.example.catalogos.Ctlg_metodos_inventario;
import com.example.ctlg_estados.Ctlg_estados;
import com.example.organizacion.Organizacion;
import com.example.utilidades.DB;


public class Productos {
	private Integer id;
	private  String nombre;
	private  BigDecimal cantidad_min;
	private  BigDecimal cantidad_maxima;
	private String descripcion;
	private String imagen;
	private String codigo;
	private Integer id_organizaciones;
	private Organizacion organizaciones;
	private Integer id_ctlg_estados;
	private Ctlg_estados ctlg_estados;
	private Integer id_ctlg_metodos_inventario;
	private Ctlg_metodos_inventario ctlg_metodos_inventario;
	private Integer id_ctlg_unidades_medidas;
	private Ctlg_unidades_medidas ctlg_unidades_medidas;
	private Integer id_ctlg_tipos_productos;
	private Ctlg_tipos_productos ctlg_tipos_productos;
	private Integer id_ctlg_iva;
	private Ctlg_iva ctlg_iva;
	
	public Productos () {

	}

	public  Productos  (Integer id) throws SQLException {
		DB db=new DB();
		PreparedStatement pst = db.prepare("select * from productos where id=?");
		pst.setInt(1,id);
		ResultSet rs=pst.executeQuery();

		if (rs.next()) {
			ResultSetMetaData md = rs.getMetaData();
			int cols = md.getColumnCount();
			HashMap<String,Object> row = new HashMap<String,Object>(cols);

			for(int i=1; i<=cols; ++i){
				row.put(md.getColumnName(i),rs.getObject(i));
			}
			row.put("ctlg_iva", new Ctlg_iva((Integer)row.get("id_ctlg_iva")));
			row.put("ctlg_tipos_productos", new Ctlg_tipos_productos((Integer)row.get("id_ctlg_tipos_productos")));
			row.put("ctlg_unidades_medidas", new Ctlg_unidades_medidas((Integer)row.get("id_ctlg_unidades_medidas")));
			row.put("ctlg_metodos_inventario", new Ctlg_metodos_inventario((Integer)row.get("id_ctlg_metodos_inventario")));
			row.put("organizaciones", new Organizacion((Integer)row.get("id_organizaciones")));
			row.put("ctlg_estados", new Ctlg_estados((Integer)row.get("id_ctlg_estados")));

			


			this.fromHashMap(row);
		} else {
			throw new SQLException("No se encontro nada");
		}
		
		

	}
	

	public static List<Productos> index () throws SQLException {
		DB db=new DB();
		PreparedStatement pst = db.prepare("select * from productos");
		ResultSet rs=pst.executeQuery();
		ResultSetMetaData md = rs.getMetaData();
		int cols = md.getColumnCount();
		List<Productos> list=new ArrayList<Productos>();
		while (rs.next()){
			HashMap<String,Object> row = new HashMap<String,Object>(cols);
			for(int i=1; i<=cols; ++i){
				row.put(md.getColumnName(i),rs.getObject(i));

			}
			row.put("ctlg_iva", new Ctlg_iva((Integer)row.get("id_ctlg_iva")));
			row.put("ctlg_tipos_productos", new Ctlg_tipos_productos((Integer)row.get("id_ctlg_tipos_productos")));
			row.put("ctlg_unidades_medidas", new Ctlg_unidades_medidas((Integer)row.get("id_ctlg_unidades_medidas")));
			row.put("ctlg_metodos_inventario", new Ctlg_metodos_inventario((Integer)row.get("id_ctlg_metodos_inventario")));
			row.put("organizaciones", new Organizacion((Integer)row.get("id_organizaciones")));
			row.put("ctlg_estados", new Ctlg_estados((Integer)row.get("id_ctlg_estados")));
			

			Productos productos=new Productos ();
			list.add(productos.fromHashMap(row));
		}
		db.close();
		return list;
	}

	private Productos fromHashMap ( HashMap<String,Object> productos) {
		this.id=(Integer)productos.get("id");
		this.nombre=(String)productos.get("nombre");
		this.cantidad_min=(BigDecimal)productos.get("cantidad_min");
		this.cantidad_maxima=(BigDecimal)productos.get("cantidad_maxima");
		this.descripcion=(String)productos.get("descripcion");
		this.imagen=(String)productos.get("imagen");
		this.codigo=(String)productos.get("codigo");
		this.organizaciones=(Organizacion)productos.get("organizaciones");
		this.ctlg_estados=(Ctlg_estados)productos.get("ctlg_estados");
		this.ctlg_metodos_inventario=(Ctlg_metodos_inventario)productos.get("ctlg_metodos_inventario");
		this.ctlg_iva=(Ctlg_iva)productos.get("ctlg_iva");
		this.ctlg_tipos_productos=(Ctlg_tipos_productos)productos.get("ctlg_tipos_productos");
		this.ctlg_unidades_medidas=(Ctlg_unidades_medidas)productos.get("ctlg_unidades_medidas");


		
		
		return this;
	}



	private Productos create() throws SQLException  {

		DB db=new DB();
		try {
			db.getConnection().setAutoCommit(false);
			PreparedStatement pst = db.prepare("INSERT INTO productos(nombre,cantidad_min,cantidad_maxima,descripcion,imagen,codigo,id_organizaciones,id_ctlg_estados,id_ctlg_metodos_inventario,id_ctlg_iva,id_ctlg_tipos_productos,id_ctlg_unidades_medidas) values(?,?,?,?,?,?,?,?,?,?,?,?)");
			pst.setString (1, this.nombre);
			pst.setBigDecimal (2, this.cantidad_min);
			pst.setBigDecimal (3, this.cantidad_maxima);
			pst.setString (4, this.descripcion);
			pst.setString (5, this.imagen);
			pst.setString (6, this.codigo);
			pst.setInt (7, this.id_organizaciones);
			pst.setInt(8,this.id_ctlg_estados);
			pst.setInt(9,this.id_ctlg_metodos_inventario);
			pst.setInt(10,this.id_ctlg_iva);
			pst.setInt(11,this.id_ctlg_tipos_productos);
			pst.setInt(12,this.id_ctlg_unidades_medidas);




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

	private Productos update() throws SQLException {
		DB db=new DB();
		PreparedStatement pst =db.prepare("UPDATE productos set nombre=?,cantidad_min=?,cantidad_maxima=?,descripcion=?,imagen=?,codigo=?,id_organizaciones=?,id_ctlg_estados=?,id_ctlg_metodos_inventario=?,id_ctlg_iva=?,id_ctlg_tipos_productos=?,id_ctlg_unidades_medidas=? where id=?");
		pst.setString (1, this.nombre);
		pst.setBigDecimal (2, this.cantidad_min );
		pst.setBigDecimal (3, this.cantidad_maxima);
		pst.setString (4, this.descripcion);
		pst.setString (5, this.imagen);
		pst.setString (6, this.codigo);
		pst.setInt(7, this.id_organizaciones);
		pst.setInt(8,this.id_ctlg_estados);
		pst.setInt(9, this.id_ctlg_metodos_inventario);
		pst.setInt(10,this.id_ctlg_iva);
		pst.setInt(11,this.id_ctlg_tipos_productos);
		pst.setInt(12,this.id_ctlg_unidades_medidas);
		pst.setInt(13, this.id);
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

	public Productos save() throws SQLException {
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
		return organizaciones;
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


	public BigDecimal getCantidad_min() {
		return cantidad_min;
	}
	public void setCantidad_min(BigDecimal cantidad_min) {
		this.cantidad_min = cantidad_min;
	}

	
	
	public BigDecimal getCantidad_maxima() {
		return cantidad_maxima;
	}
	public void setCantidad_maxima(BigDecimal cantidad_maxima) {
		this.cantidad_maxima = cantidad_maxima;
	}

	
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
	public Ctlg_metodos_inventario getCtlg_metodos_inventario() {
		return ctlg_metodos_inventario;
	}
	public Integer getId_ctlg_metodos_inventario() {
		return id_ctlg_metodos_inventario;
	}
	public void setId_ctlg_metodos_inventario(Integer id_ctlg_metodos_inventario) {
		this.id_ctlg_metodos_inventario = id_ctlg_metodos_inventario;
	}

	
	public Ctlg_tipos_productos getCtlg_tipos_productos() {
		return ctlg_tipos_productos;
	}
	public Integer getId_ctlg_tipos_productos() {
		return id_ctlg_tipos_productos;
	}
	public void setId_ctlg_tipos_productos(Integer id_ctlg_tipos_productos) {
		this.id_ctlg_tipos_productos = id_ctlg_tipos_productos;
	}

	
	public Ctlg_iva getCtlg_iva() {
		return ctlg_iva;
	}
	public Integer getId_ctlg_iva() {
		return id_ctlg_iva;
	}
	public void setId_ctlg_iva(Integer id_ctlg_iva) {
		this.id_ctlg_iva = id_ctlg_iva;
	}
	
	public Ctlg_unidades_medidas getCtlg_unidades_medidas() {
		return ctlg_unidades_medidas;
	}
	public Integer getId_ctlg_unidades_medidas() {
		return id_ctlg_unidades_medidas;
	}
	public void setId_ctlg_unidades_medidas(Integer id_ctlg_unidades_medidas) {
		this.id_ctlg_unidades_medidas = id_ctlg_unidades_medidas;
	}

	
}	
	
	



