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
	private static final String INSERT_CATEGORIE = "INSERT INTO Categories(libelle) VALUES (?)";
	
	private static final String SELECT_ALL_CATEGORIES = "SELECT no_categorie, libelle FROM Categories";
	private static final String SELECT_CATEGORIE_BY_ID = "SELECT no_categorie, libelle FROM Categories WHERE no_categorie = ?";
	
	private static final String UPDATE_CATEGORIE = "UPDATE Categories SET libelle = ? WHERE no_categorie = ?";
	
	private static final String DELETE_CATEGORIE = "DELETE FROM Categories WHERE no_categorie = ?";
	
	/* CONSTUCTEUR */
	public CategorieDAOJdbcImpl() { }
	
	/*  CRUD  */
	@Override
	public void insertCategorie(Categorie categorie) throws BusinessException {
		// Test pour vérifier qu'on insère bien une catégorie
		if ( categorie == null ) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(10000);
			throw businessException;
		}
		
		try ( Connection cnx = ConnectionProvider.getConnection(); 
			  PreparedStatement pstmt = cnx.prepareStatement( INSERT_CATEGORIE, PreparedStatement.RETURN_GENERATED_KEYS );
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
			  ResultSet rs 	 = stmt.executeQuery( SELECT_ALL_CATEGORIES );
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
		Categorie cat = null;
		
		try ( Connection cnx = ConnectionProvider.getConnection();
			  PreparedStatement pstmt = cnx.prepareStatement(SELECT_CATEGORIE_BY_ID);
			)
		{
			pstmt.setInt(1, noCategorie);
			
			ResultSet rs = pstmt.executeQuery() ;
			
			if ( rs.next() ) {
								
					cat = new Categorie( rs.getString("libelle") );
				}
			rs.close();
		} 
		catch ( SQLException e ) {
			e.printStackTrace();
		}
			
		return cat;
	}

	@Override
	public void updateCategorie(Categorie categorie) {
		
		try ( Connection cnx = ConnectionProvider.getConnection();
			  PreparedStatement pstmt = cnx.prepareStatement(UPDATE_CATEGORIE);
			)
		{
			pstmt.setString( 1, categorie.getLibelle() );
			pstmt.setInt( 2, categorie.getNoCategorie() );
			pstmt.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCategorie(int noCategorie) {
		
		try ( Connection cnx = ConnectionProvider.getConnection();
			  PreparedStatement pstmt = cnx.prepareStatement(DELETE_CATEGORIE);
			)
		{
			pstmt.setInt( 1, noCategorie );
			pstmt.executeUpdate();
		} 
		catch (SQLException e) {
				e.printStackTrace();
		}
	}

}