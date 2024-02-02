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
	
	private final String INSERT_ARTICLEVENDUS="INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) " 
			+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_ARTICLEVENDUS="SELECT INTO ARTICLE_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_vente, prix_initial, no_utilisateur, no_categorie) FROM ARTICLE_VENDUS";
	private static final String SELECT_ARTICLEVENDUS_BY_ID="SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLE_VENDUS WHERE no_article=?";
	private static final String UPDATE_ARTICLEVENDUS="UPDATE ARTICLE_VENDU SET nom_article=?, description=?, date_debut_encheres=?, date_fin_encheres=?, prix_initial=?, prix_vente=?, no_categorie=? WHERE no_article=?";
	private static final String DELETE_ARTICLEVENDUS="DELETE FROM ARTICLE_VENDU WHERE no_article=?";
	private static final String UPDATE_ARTICLE_DELETED_USER = "UPDATE ARTICLES_VENDUS SET no_utilisateur = null WHERE no_utilisateur = ?";

	public void updateArticleDeteletedUtilisateur(int userId) throws BusinessException{
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ARTICLE_DELETED_USER);
			pstmt.setInt(1, userId);
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void insertArticleVendu (ArticleVendu articleVendu) throws BusinessException {
		
		try(Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement ps=cnx.prepareStatement(INSERT_ARTICLEVENDUS,PreparedStatement.RETURN_GENERATED_KEYS);) 
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
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
	}

	@Override
	public List<ArticleVendu> selectAllArticleVendu() throws BusinessException 
	{
		List<ArticleVendu> listeArt = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection(); 
			Statement stmt= cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ARTICLEVENDUS);) 
		{
			while(rs.next()) 
			{
				ArticleVendu art = new ArticleVendu
					(
						rs.getString( "nomArticle" ), 
						rs.getString( "description" ), 
						rs.getDate("date_debut_encheres"), 
						rs.getDate("date_fin_encheres"), 
						rs.getInt( "prix_vente" ), 
						rs.getInt( "prix_initial" ), 
						rs.getInt( "no_utilisateur" ),
						rs.getInt("no_categorie")
					);
				
				listeArt.add(art); 
			}
			
		}
		catch ( SQLException e ) 
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ECHEC);
			throw businessException;
		}
		return listeArt;
	}

	@Override
	public ArticleVendu selectArticleVenduById(int noArticleVendu) throws BusinessException 
	{
		ArticleVendu art = null;
			
			try (Connection cnx = ConnectionProvider.getConnection();
				 PreparedStatement ps = cnx.prepareStatement(SELECT_ARTICLEVENDUS_BY_ID);
				)
			{
				ps.setInt(1, noArticleVendu);
				ResultSet rs = ps.executeQuery();
	
				if (rs.next()) 
				{
					art = new ArticleVendu
						( 
							rs.getInt("no_article"), 
							rs.getString( "nomArticle" ), 
							rs.getString( "description" ), 
							rs.getDate("date_debut_encheres"), 
							rs.getDate("date_fin_encheres"), 
							rs.getInt( "prix_vente" ), 
							rs.getInt( "prix_initial" ), 
							rs.getInt( "no_utilisateur" ),
							rs.getInt("no_categorie")		
						);
				}
				rs.close();
			} 
			catch ( SQLException e ) 
			{
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ECHEC);
				throw businessException;
			}	
			return art;
		}

	@Override
	public void deleteArticleVendu(int noArticleVendu) throws BusinessException 
	{
		try (Connection cnx = ConnectionProvider.getConnection();
				  PreparedStatement ps = cnx.prepareStatement(DELETE_ARTICLEVENDUS);
			)
			{
				ps.setInt(1, noArticleVendu);
				ps.executeUpdate();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.SUPPRIMER_ARTICLE_ECHEC);
				throw businessException;
			}
	}
	
	@Override
	public void updateArticleVendu(ArticleVendu articleVendu) throws BusinessException 
	{
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement ps = cnx.prepareStatement(UPDATE_ARTICLEVENDUS);
			)
		{	
			ps.setInt(1,articleVendu.getNoArticle());
			ps.setString(2, articleVendu.getNomArticle()); 
			ps.setString(3,articleVendu.getDescription()); 
			ps.setDate(4,(Date) articleVendu.getDateDebutEncheres()); 
			ps.setDate(5,(Date) articleVendu.getDateFinEncheres()); 
			ps.setInt(6,articleVendu.getPrixVente()); 
			ps.setInt(7,articleVendu.getMiseAPrix()); 
			ps.setInt(8,articleVendu.getNoUtilisateur());
			ps.setInt(9,articleVendu.getNoCategorie());
			ps.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.MAJ_ARTICLE_ECHEC);
			throw businessException;
		}
	}
}