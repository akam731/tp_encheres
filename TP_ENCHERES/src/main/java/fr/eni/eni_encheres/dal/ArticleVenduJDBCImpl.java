package fr.eni.eni_encheres.dal;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bo.ArticleVendu;
import fr.eni.eni_encheres.bo.Categorie;
import fr.eni.eni_encheres.bo.Utilisateur;


public class ArticleVenduJDBCImpl implements ArticleVenduDAO {
	
	private final String AJOUT_ARTICLEVENDU="INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial"
			+ "VALUES (?, ?, ?, ?, ?)";

	@Override
	public void insert (ArticleVendu articleVendu) throws BusinessException {
		
		try(Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement ps=cnx.prepareStatement(AJOUT_ARTICLEVENDU,PreparedStatement.RETURN_GENERATED_KEYS);) 
		{
				ps.setString(1, articleVendu.getNomArticle());
		        ps.setString(2, articleVendu.getDescription());
		        ps.setTimestamp(3, (Timestamp.valueOf(articleVendu.getDateDebutEncheres())));	
		        ps.setTimestamp(4, (Timestamp.valueOf(articleVendu.getDateFinEncheres())));
		        ps.setInt(5, articleVendu.getMiseAPrix());
		        //noUtilisateur?
		        //noCategorie?
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArticleVendu selectById(int noArticleVendu) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int noArticleVendu) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ArticleVendu articleVendu) throws BusinessException {
		// TODO Auto-generated method stub
		
	}


}