package Test;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bo.ArticleVendu;
import fr.eni.eni_encheres.dal.ArticleVenduDAO;
import fr.eni.eni_encheres.dal.DAOFactory;

/**
 * Servlet implementation class testBBD
 */
@WebServlet("/testBBD")
public class testBBD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public testBBD() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Déclaration et instanciation de la DAO
				ArticleVenduDAO articleDAO = DAOFactory.getArticleVenduDAO();
				
				//Instanciation du jeu d'essai 
				ArticleVendu a1 = new ArticleVendu("pcgamer", "pc tres cool", LocalDateTime.of(2024,01,31,14,00), LocalDateTime.of(2024,01,31,16,00), 1, true, 2);
				
				System.out.println( "Ajout des articles... " );
				
				try {
					articleDAO.insert(a1);
				} catch (BusinessException e) {
					e.printStackTrace();
				}
				
				System.out.println( "Article ajouté  : " + a1.toString() );
			}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
