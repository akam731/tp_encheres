package fr.eni.eni_encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bo.Categorie;

public class CategorieDAOJdbcImpl implements CategorieDAO {

	/* REQUETES */
	private static final String ADD_CATEGORIE = "INSERT INTO Categories(libelle) VALUES (?)";
	
	private static final String FIND_ALL_CATEGORIES = "SELECT no_categorie, libelle FROM Categories";
	private static final String FIND_CATEGORIE_BY_ID = "";
	
	private static final String UPDATE_CATEGORIE = "";
	
	private static final String DELETE_CATEGORIE = "";
	
	/* CONSTUCTEUR */
	public CategorieDAOJdbcImpl() { }
	
	/*  CRUD  */
	@Override
	public void insertCategorie(Categorie categorie) throws BusinessException {
		
		try ( Connection cnx = ConnectionProvider.getConnection(); 
			  PreparedStatement pstmt = cnx.prepareStatement( ADD_CATEGORIE, PreparedStatement.RETURN_GENERATED_KEYS );
			) 
		{
			pstmt.setString( 1, categorie.getLibelle() );
			
			int nbRows = pstmt.executeUpdate();
			if ( nbRows == 1 ) {
				ResultSet rs = pstmt.getGeneratedKeys();
				
				if ( rs.next() ) {
					categorie.setNoCategorie( rs.getInt(1) );
				}
				rs.close();
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Categorie> selectAllCategories() {
		List<Categorie> listeCategories = null;
		Categorie cat = null;
		
		try ( Connection cnx = ConnectionProvider.getConnection();
			  Statement stmt = cnx.createStatement();
			  ResultSet rs 	 = stmt.executeQuery( FIND_ALL_CATEGORIES );
			)
		{
			while ( rs.next() ) {
				
				if ( listeCategories == null ) {
					listeCategories = new ArrayList<>();
				}
				
				cat = new Categorie( rs.getInt("no_categorie"), rs.getString("libelle") );
				listeCategories.add(cat);
			}
			
		} 
		catch ( SQLException e ) {
			e.printStackTrace();
		}
		
		return listeCategories;
	}
	@Override
	public Categorie selectCategorieById(int noCategorie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCategorie(Categorie categorie) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCategorie(int noCategorie) {
		// TODO Auto-generated method stub
		
	}
}
