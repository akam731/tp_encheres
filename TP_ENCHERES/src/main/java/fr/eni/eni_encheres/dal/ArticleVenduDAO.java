package fr.eni.eni_encheres.dal;

import java.util.List;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bo.ArticleVendu;

public interface ArticleVenduDAO {
	
	public void insert(ArticleVendu articleVendu) throws BusinessException;
	public List<ArticleVendu> selectAll() throws BusinessException;
	public  ArticleVendu selectById(int noArticleVendu)throws BusinessException;
	public void delete(int noArticleVendu)throws BusinessException;
	public void update(ArticleVendu articleVendu)throws BusinessException;
	
}
