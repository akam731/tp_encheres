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

@WebServlet("/acceuil")
public class acceuil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("pseudo") == null) {
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
					
					Utilisateur utilisateur = user.getUserBy("pseudo", userPseudo);
					
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
						
						response.sendRedirect("acceuil");
						
					}else {
						RequestDispatcher rd = request.getRequestDispatcher("/jsp/acceuil.jsp");
						rd.forward(request, response);
					}
				} catch (BusinessException e) {
					e.printStackTrace();
				}
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/acceuil.jsp");
				rd.forward(request, response);
			}
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/acceuil.jsp");
			rd.forward(request, response);
		}
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
