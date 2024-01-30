package fr.eni.eni_encheres.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bll.EniEnchereManager;
import fr.eni.eni_encheres.bo.Utilisateur;


@WebServlet("/ProfilUtilisateur")
public class ProfilUtilisateur extends HttpServlet {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        // Récupérer le pseudo de l'utilisateur à afficher 
	        String targetUsername = request.getParameter("username");
	        EniEnchereManager user = new EniEnchereManager();
	        Utilisateur utilisateur = new Utilisateur();
			try {
				
				Utilisateur utilisateur = user.getUserBy("pseudo", targetUsername);
			}catch (BusinessException e) {
                e.printStackTrace();
            }

	        // Passer les informations à la JSP
	        request.setAttribute("username", targetUsername);
	        request.setAttribute("firstName", utilisateur.getPrenom());
	        request.setAttribute("lastName", utilisateur.getNom());
	        request.setAttribute("email", utilisateur.getEmail());
	        request.setAttribute("phoneNumber", utilisateur.getTelephone());
	        request.setAttribute("street", utilisateur.getRue());
	        request.setAttribute("postalCode", utilisateur.getCodePostal());
	        request.setAttribute("city", utilisateur.getVille());

	        // Rediriger vers la page de profil
	        request.getRequestDispatcher("/profile.jsp").forward(request, response);
	    }

}
