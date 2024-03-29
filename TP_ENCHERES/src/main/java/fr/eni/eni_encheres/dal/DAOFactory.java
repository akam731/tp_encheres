package fr.eni.eni_encheres.dal;

public abstract class DAOFactory {
	
	public static ArticleVenduDAO getArticleVenduDAO() 
	{
		return new ArticleVenduJDBCImpl();
	}

	public static EncheresArticlesDAO getEncheresArticlesDAO()
	{
		return new EncheresArticlesDAOJdbcImpl();
	}
	
	public static EncheresDAO getEncheresDAO()
	{
		return new EncheresDAOJdbcImpl();
	}
	
	public static CategorieDAO getCategorieDAO()
	{
		return new CategorieDAOJdbcImpl();
	}
	
	public static RetraitDAO getRetraitDAO()
	{
		return new RetraitDAOJdbcImpl();
	}
	
}
