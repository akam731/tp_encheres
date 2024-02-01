package fr.eni.eni_encheres.dal;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bo.ArticleVendu;


public class ArticleVenduJDBCImpl implements ArticleVenduDAO {
	
	private final String AJOUT_ARTICLEVENDUS="INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) " 
			+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
	private final String SELECT_DATE_DEBUT = "SELECT date_debut_encheres FROM Articles_vendus";
	@Override
	public void insert (ArticleVendu articleVendu) throws BusinessException {
		
		try(Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement ps=cnx.prepareStatement(AJOUT_ARTICLEVENDUS,PreparedStatement.RETURN_GENERATED_KEYS);) 
		{
				ps.setString(1, articleVendu.getNomArticle());
		        ps.setString(2, articleVendu.getDescription());
		        DateTimeFormatter dateTime = DateTimeFormatter.ofPattern(SELECT_DATE_DEBUT);
//		        ps.setDate(3, articleVendu.getDateDebutEncheres() ) );	
//		        ps.setDate(4, articleVendu.getDateFinEncheres() );
		        ps.setInt(5, articleVendu.getMiseAPrix());
		        ps.setInt(6, articleVendu.getNoUtilisateur());
		        ps.setInt(7, articleVendu.getNoCategorie());
		        
		        int nbRows = ps.executeUpdate();
		        
				if ( nbRows == 1 ) 
				{
	                ResultSet rs = ps.getGeneratedKeys();

	                if ( rs.next() )
	                {
	                	articleVendu.setNoArticle(rs.getInt(1));
	                }

	                rs.close();
				}
			}
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public List<ArticleVendu> selectAll() throws BusinessException {
		return null;
	}

	@Override
	public ArticleVendu selectById(int noArticleVendu) throws BusinessException {
		return null;
	}

	@Override
	public void delete(int noArticleVendu) throws BusinessException {
		
	}

	@Override
	public void update(ArticleVendu articleVendu) throws BusinessException {
		
	}


}
