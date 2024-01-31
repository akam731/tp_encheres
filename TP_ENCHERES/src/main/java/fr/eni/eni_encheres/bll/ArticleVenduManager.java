package fr.eni.eni_encheres.bll;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bo.ArticleVendu;
import fr.eni.eni_encheres.dal.ArticleVenduDAO;
import fr.eni.eni_encheres.dal.DAOFactory;



public class ArticleVenduManager {
	
	private static ArticleVenduDAO avDAO;
	
	public ArticleVenduManager() 
	{
		avDAO=DAOFactory.getArticleVenduDAO();
	}
	
	public void addArticleVendu (ArticleVendu articleVendu) throws BusinessException {
		if (articleVendu.getNoArticle() != null) 
		{
			throw new BusinessException();
		}
		avDAO.insert(articleVendu);

	}
}
