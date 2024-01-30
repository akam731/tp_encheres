package fr.eni.eni_encheres.dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionProvider {

	private static DataSource datasource;
	
	static
	{
		try {
			Context context = new InitialContext();
			//Recherche de la datasource
			ConnectionProvider.datasource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException
	{
		return ConnectionProvider.datasource.getConnection();
	}	
}
