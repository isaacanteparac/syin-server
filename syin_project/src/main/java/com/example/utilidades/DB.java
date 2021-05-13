package com.example.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;


public class DB {
	private String url = "jdbc:mysql://localhost:3306/syin?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String user = "root";
	private String password = "123456";
	private Connection connection=null;

	public DB() throws SQLException {
		this.connection=DriverManager.getConnection(url, user, password);
	}

	public void close() throws SQLException {
		this.connection.close();
	}

	public Connection getConnection()  {
		return this.connection;
	}


	public PreparedStatement prepare(String query) throws SQLException {
		PreparedStatement pst = this.connection.prepareStatement(query,  Statement.RETURN_GENERATED_KEYS);
		return pst;
	}


	public static HashMap<String, Object> row(PreparedStatement pst) throws SQLException {
		ResultSet rs = pst.executeQuery();
		HashMap<String,Object> row = new HashMap<String,Object>();
		if (rs.next() == false) {
			throw new SQLException("No se encontro nada");
		} else {
			ResultSetMetaData md = rs.getMetaData();
			int columns = md.getColumnCount();
			do {
				for(int i=1; i<=columns; ++i){
					row.put(md.getColumnName(i),rs.getObject(i));
				}
				break;
			} while (rs.next());
		}
		return row;
	}


}
