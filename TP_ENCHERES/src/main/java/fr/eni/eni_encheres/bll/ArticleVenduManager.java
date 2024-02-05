package fr.eni.eni_encheres.bll;

import java.util.Date;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bo.ArticleVendu;
import fr.eni.eni_encheres.dal.ArticleVenduDAO;
import fr.eni.eni_encheres.dal.DAOFactory;



public class ArticleVenduManager 
{
	
	private static ArticleVenduDAO avDAO;
	
	public void updateArticleDeteletedUtilisateur(int idUser) throws BusinessException{
		
		avDAO.updateArticleDeteletedUtilisateur(idUser);
		
	}
	
	public ArticleVenduManager() 
	{
		avDAO=DAOFactory.getArticleVenduDAO();
	}
	
	//Methode pour ajouter un article 
	public ArticleVendu addArticleVendu (String nomArticle, String description, Date debutEncheres, Date finEncheres, int miseAPrix, int noUtilisateur, int noCategorie ) throws BusinessException 
	{

		BusinessException businessException = new BusinessException();
		ArticleVendu nouvelArticleVendu = null;
		
		if(!businessException.hasErreurs()) {
			
			
			nouvelArticleVendu = new ArticleVendu();
            nouvelArticleVendu.setNoCategorie(noCategorie);
            nouvelArticleVendu.setNomArticle(nomArticle);
            nouvelArticleVendu.setDescription(description);
            nouvelArticleVendu.setDateDebutEncheres(debutEncheres);
            nouvelArticleVendu.setDateFinEncheres(finEncheres);
            nouvelArticleVendu.setMiseAPrix(miseAPrix);
            nouvelArticleVendu.setNoUtilisateur(noUtilisateur);
            
            nouvelArticleVendu = avDAO.insertArticleVendu(nouvelArticleVendu);
            
            
            
		}else {
			throw businessException;
		}
		return nouvelArticleVendu;
		
	}
}




