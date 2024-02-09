package fr.eni.eni_encheres.servlets;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bll.EniEnchereManager;
import fr.eni.eni_encheres.bo.Utilisateur;


@WebServlet("/utilisateur")
public class ProfilUtilisateur extends HttpServlet {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
HttpSession session = request.getSession();
		
		// Auto - Connexion par Cookies
		Cookie[] cookies = request.getCookies();
		
		
		String userPseudo = null;
		String userMdp = null;
		
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if("User".equals(cookie.getName())) {
					userPseudo = URLDecoder.decode(cookie.getValue(), "UTF-8");
				}
				if("PassWord".equals(cookie.getName())) {
					userMdp = URLDecoder.decode(cookie.getValue(), "UTF-8");
				}
			}
		}
		
		if(userMdp != null && userPseudo != null) {
			EniEnchereManager user = new EniEnchereManager();
			try {
				
				Utilisateur utilisateur = user.getUserByPseudo(userPseudo);
				
				if(userMdp.equals(utilisateur.getMotDePasse())) {
					
					session.setAttribute("isConnected", true);
					session.setAttribute("noUtilisateur", utilisateur.getNoUtilisateur());
					session.setAttribute("pseudo", userPseudo);
					session.setAttribute("nom", utilisateur.getNom());
					session.setAttribute("prenom", utilisateur.getPrenom());
					session.setAttribute("mail", utilisateur.getEmail());
					session.setAttribute("tel", utilisateur.getTelephone());
					session.setAttribute("rue", utilisateur.getRue());
					session.setAttribute("codePostal", utilisateur.getCodePostal());
					session.setAttribute("ville", utilisateur.getVille());		
					session.setAttribute("mdp", utilisateur.getMotDePasse());		
					session.setAttribute("admin", utilisateur.isAdministrateur());
					session.setAttribute("credits", utilisateur.getCredit());	
				}
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
		
		
		
			
		
			
		
		
			// Récupérer le pseudo de l'utilisateur à afficher 
	        String targetUsername = request.getParameter("username");
	        EniEnchereManager user = new EniEnchereManager();
	        Utilisateur utilisateur = new Utilisateur();
			try {
				
				utilisateur = user.getUserByPseudo(targetUsername);
				
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
	        RequestDispatcher rs = request.getRequestDispatcher("/jsp/utilisateur.jsp");
	        rs.forward(request, response);
	    }

}
