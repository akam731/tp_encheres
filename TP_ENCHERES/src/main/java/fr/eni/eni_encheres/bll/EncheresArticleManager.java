package fr.eni.eni_encheres.bll;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bo.Enchere;
import fr.eni.eni_encheres.dal.DAOFactory;
import fr.eni.eni_encheres.dal.EncheresArticlesDAO;

public class EncheresArticleManager {

	private static EncheresArticlesDAO enchereDAO;
	
	public EncheresArticleManager() throws BusinessException {
		enchereDAO = DAOFactory.getEncheresArticlesDAO();
	}
	
	public void setNewEnchere(Enchere enchere) throws BusinessException{	
		enchereDAO.setNewEnchere(enchere);	
	}
	
	public Enchere getEnchereById(int userID,int articleID) throws BusinessException{
		return null;
		
	}
	
	public Enchere getEnchereByArticleId(int articleID) throws BusinessException{
		
		Enchere enchere = enchereDAO.getEnchereByArticleId(articleID);
		return enchere;	
		
	}
	
	public void deleteEnchere(int userID, int articleID) throws BusinessException{
		
	}
	
	public void editEcnhereById(Enchere enchere, int userID, int articleID) throws BusinessException{
		enchereDAO.editEcnhereById(enchere, userID, articleID);
	}
	
}
