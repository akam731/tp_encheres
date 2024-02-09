package fr.eni.eni_encheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bo.ArticleVendu;
import fr.eni.eni_encheres.bo.Enchere;

public class EncheresArticlesDAOJdbcImpl implements EncheresArticlesDAO{

	private static final String SELECT_ENCHERE_BY_ARTICLE_ID = "SELECT * FROM ENCHERES WHERE no_article = ?;";
	private static final String SET_NEW_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES ( ?, ?, ?, ?);";
	private static final String UPDATE_ENCHERE_BY_ID = "UPDATE ENCHERES SET no_utilisateur = ?, no_article = ?, date_enchere = ?, montant_enchere = ? WHERE no_utilisateur = ? AND no_article = ?";

	@Override
	public void setNewEnchere(Enchere enchere) throws BusinessException {
		

		
		try(Connection cnx = ConnectionProvider.getConnection()){
		
			PreparedStatement stmt= cnx.prepareStatement(SET_NEW_ENCHERE);
			stmt.setInt(1, enchere.getNo_Utilisateur());
			stmt.setInt(2, enchere.getNo_Article());
			stmt.setDate(3, (Date)enchere.getDateEnchere());
			stmt.setInt(4, enchere.getMontantEnchere());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Enchere getEnchereById(int id) throws BusinessException {
		return null;
	}
	
	@Override
	public Enchere getEnchereByArticleId(int articleID) throws BusinessException {
		
		Enchere enchere = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement stmt= cnx.prepareStatement(SELECT_ENCHERE_BY_ARTICLE_ID);
			stmt.setInt(1, articleID);
			ResultSet rs = stmt.executeQuery();
		
			while(rs.next()) 
			{
				enchere = new Enchere
					(
						rs.getInt( "no_utilisateur" ), 
						rs.getInt("no_article"), 
						rs.getDate("date_enchere"), 
						rs.getInt("montant_enchere")
					);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return enchere;
	}
	
	@Override
	public void deleteEnchere(int userID, int articleID) throws BusinessException {
		
	}

	@Override
	public Enchere editEcnhereById(Enchere enchere, int userID, int articleID) throws BusinessException {
		
		try(Connection cnx = ConnectionProvider.getConnection()){
		    PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ENCHERE_BY_ID);

		    pstmt.setInt(1, enchere.getNo_Utilisateur());
		    pstmt.setInt(2, enchere.getNo_Article());
		    pstmt.setDate(3, (Date) enchere.getDateEnchere());
		    pstmt.setInt(4, enchere.getMontantEnchere());
		    pstmt.setInt(5, userID);
		    pstmt.setInt(6, articleID);
		    pstmt.executeUpdate();
		    
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
