package fr.eni.eni_encheres.servlets;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

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

@WebServlet("/connexion")
public class connexion extends HttpServlet {
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
					
					response.sendRedirect("acceuil");
					
				}else {
					RequestDispatcher rd = request.getRequestDispatcher("/jsp/connexion.jsp");
					rd.forward(request, response);
				}
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}else {
			if(session.getAttribute("pseudo") != null) {
				response.sendRedirect("acceuil");
			}else {
				RequestDispatcher rs = request.getRequestDispatcher("jsp/connexion.jsp");
				rs.forward(request, response);
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		

		String pseudo = (String) request.getParameter("pseudo"); 
		String motDePasse = (String) request.getParameter("motDePasse");
		String souvenir = (String) request.getParameter("souvenir");
		
		
		if(pseudo != null && motDePasse != null) {
			
			EniEnchereManager user = new EniEnchereManager();
			try {
				
				Utilisateur utilisateur = user.getUserByPseudo(pseudo);
				
				if(motDePasse.equals(utilisateur.getMotDePasse())) {
					
					session.setAttribute("isConnected", true);
					session.setAttribute("noUtilisateur", utilisateur.getNoUtilisateur());
					session.setAttribute("pseudo", pseudo);
					session.setAttribute("nom", utilisateur.getNom());
					session.setAttribute("prenom", utilisateur.getPrenom());
					session.setAttribute("mail", utilisateur.getEmail());
					session.setAttribute("tel", utilisateur.getTelephone());
					session.setAttribute("rue", utilisateur.getRue());
					session.setAttribute("codePostal", utilisateur.getCodePostal());
					session.setAttribute("ville", utilisateur.getVille());
					session.setAttribute("mdp", utilisateur.getMotDePasse());
					session.setAttribute("credits", utilisateur.getCredit());
					session.setAttribute("admin", utilisateur.isAdministrateur());
					
					
			        if ("on".equals(souvenir)) {
			        	Cookie userPseudo = new Cookie("User", URLEncoder.encode(pseudo, "UTF-8"));	
			        	Cookie userMdp = new Cookie("PassWord", URLEncoder.encode(motDePasse, "UTF-8"));	
			        	
			        	userPseudo.setMaxAge(365 * 24 * 60 * 60);
			        	userMdp.setMaxAge(365 * 24 * 60 * 60);
			        	
			        	response.addCookie(userPseudo);
			        	response.addCookie(userMdp);
			        }
					
					response.sendRedirect("acceuil");
					
				}else {
					request.setAttribute("errorConnexion", "Vos identifiants ne correspondent à aucun compte enregistré !");
					RequestDispatcher rd = request.getRequestDispatcher("/jsp/connexion.jsp");
					rd.forward(request, response);
				}
				
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
		}else {
			request.setAttribute("errorConnexion", "Tous les champs doivent être remplis !");
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/connexion.jsp");
			rd.forward(request, response);
		}
		
		
	}
	
}
