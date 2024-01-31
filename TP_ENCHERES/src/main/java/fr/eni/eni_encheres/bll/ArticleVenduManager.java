package fr.eni.eni_encheres.bll;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bo.ArticleVendu;
import fr.eni.eni_encheres.bo.Categorie;
import fr.eni.eni_encheres.bo.Retrait;
import fr.eni.eni_encheres.bo.Utilisateur;
import fr.eni.eni_encheres.dal.ArticleVenduDAO;
import fr.eni.eni_encheres.dal.DAOFactory;



public class ArticleVenduManager {
	
	ArticleVenduDAO avDAO;
	
	public ArticleVenduManager() 
	{
		avDAO=DAOFactory.getArticleVenduDAO();
	}
	
	public void enregistrerArticleVendu (ArticleVendu articleVendu, Retrait retrait, Categorie noCategorie) throws BusinessException {
		ArticleVendu article = new ArticleVendu();
		avDAO.insert(article, retrait, noCategorie);
	}
}
