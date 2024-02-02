package fr.eni.eni_encheres.dal;

import java.util.List;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bo.ArticleVendu;



public interface ArticleVenduDAO {
	
	public void insertArticleVendu(ArticleVendu articleVendu) throws BusinessException;
	public List<ArticleVendu> selectAllArticleVendu() throws BusinessException;
	public  ArticleVendu selectArticleVenduById(int noArticleVendu)throws BusinessException;
	public void deleteArticleVendu(int noArticleVendu)throws BusinessException;
	public void updateArticleVendu(ArticleVendu articleVendu)throws BusinessException;
	
}