package fr.eni.eni_encheres.dal;

public abstract class DAOFactory {
	
	public static ArticleVenduDAO getArticleVenduDAO() 
	{
		return new ArticleVenduJDBCImpl();
	}

	public static EncheresDAO getEncheresDAO()
	{
		return new EncheresDAOJdbcImpl();
	}
	
}
