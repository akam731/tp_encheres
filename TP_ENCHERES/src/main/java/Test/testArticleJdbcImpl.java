package Test;

import java.time.LocalDateTime;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bo.ArticleVendu;
import fr.eni.eni_encheres.dal.ArticleVenduDAO;
import fr.eni.eni_encheres.dal.DAOFactory;

public class testArticleJdbcImpl {

	public static void main(String[] args) throws BusinessException 
	{
		//Déclaration et instanciation de la DAO
		ArticleVenduDAO articleDAO = DAOFactory.getArticleVenduDAO();
		
		//Instanciation du jeu d'essai 
		ArticleVendu a1 = new ArticleVendu("pcgamer", "pc tres cool", LocalDateTime.of(2024,01,31,14,00), LocalDateTime.of(2024,01,31,16,00), 1, true, 2);
		
		System.out.println( "Ajout des articles... " );
		
		articleDAO.insert(a1);
		
		System.out.println( "Article ajouté  : " + a1.toString() );
	}

}
