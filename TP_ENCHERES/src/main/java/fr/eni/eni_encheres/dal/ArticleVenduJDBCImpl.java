package fr.eni.eni_encheres.dal;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bo.ArticleVendu;




public class ArticleVenduJDBCImpl implements ArticleVenduDAO {
	
	private final String AJOUT_ARTICLEVENDUS="INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) " 
			+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_SELECT_ALL="SELECT INTO ARTICLE_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_vente, prix_initial, no_utilisateur, no_categorie) FROM ARTICLE_VENDUS WHERE idArticle=?";
	private static final String SQL_SELECT_BY_ID="";
	private static final String SQL_UPDATE="";
	private static final String SQL_DELETE="";
	
	@Override
	public void insert (ArticleVendu articleVendu) throws BusinessException {
		
		try(Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement ps=cnx.prepareStatement(AJOUT_ARTICLEVENDUS,PreparedStatement.RETURN_GENERATED_KEYS);) 
		{
				ps.setString(1, articleVendu.getNomArticle());
		        ps.setString(2, articleVendu.getDescription());
		        ps.setDate(3, (Date) articleVendu.getDateDebutEncheres());;	
		        ps.setDate(4, (Date) articleVendu.getDateFinEncheres());
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
	public List<ArticleVendu> selectAll() throws BusinessException 
	{
		List<ArticleVendu> listeArt = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection(); 
			Statement stmt= cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL);) 
		{
			while( rs.next() ) 
			{
				ArticleVendu art = new ArticleVendu
					(
						rs.getString( "nomArticle" ), 
						rs.getString( "description" ), 
						rs.getDate("date_debut_encheres"), 
						rs.getDate("date_fin_encheres"), 
						rs.getInt	( "prix_vente" ), 
						rs.getInt	( "prix_initial" ), 
						rs.getInt( "no_utilisateur" ),
						rs.getInt("no_categorie")
					);
				
				listeArt.add(art); 
			}
			
		}
		catch ( SQLException e ) 
		{
			throw new BusinessException();
		}
		return listeArt;
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
