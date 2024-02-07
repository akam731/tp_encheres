package fr.eni.eni_encheres.dal;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bo.Enchere;

public interface EncheresArticlesDAO {
	
	public void setNewEnchere(Enchere enchere) throws BusinessException;
	public Enchere getEnchereById(int id) throws BusinessException;
	public void deleteEnchere(int userID, int articleID) throws BusinessException;
	public Enchere editEcnhereById(Enchere enchere, int userID, int articleID) throws BusinessException;
	public Enchere getEnchereByArticleId(int articleID) throws BusinessException;
}
