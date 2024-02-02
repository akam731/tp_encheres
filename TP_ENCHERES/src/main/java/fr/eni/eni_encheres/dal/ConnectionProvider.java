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
		Context context; 
		try 
		{
			context = new InitialContext();
			//Recherche de la datasource
			datasource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");	
		} 
		catch (NamingException e) 
		{
			e.printStackTrace();
			throw new RuntimeException("Impossible d'acceder à la BDD");
		}
	}
	
	public static Connection getConnection() throws SQLException
	{
		return datasource.getConnection();
	}	
}
