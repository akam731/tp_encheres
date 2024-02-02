package fr.eni.eni_encheres.servlets;


import java.io.IOException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bll.ArticleVenduManager;
import fr.eni.eni_encheres.bll.EniEnchereManager;
import fr.eni.eni_encheres.bo.ArticleVendu;
import fr.eni.eni_encheres.bo.Utilisateur;


@WebServlet("/VenteArticle")
public class VenteArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
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
					if(session.getAttribute("pseudo") == null) {
						response.sendRedirect("acceuil");
					}else {
						RequestDispatcher rd = request.getRequestDispatcher("/jsp/AfficherVenteArticle.jsp");
						rd.forward(request, response);	
					}	
				}
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}else {
			if(session.getAttribute("pseudo") == null) {
				response.sendRedirect("acceuil");
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/AfficherVenteArticle.jsp");
				rd.forward(request, response);	
			}
		}
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		
		Date debutEncheres;
        Date finEncheres;
        String nomArticle; 
        String description; 
        String rue;
        String codePostal;
        String ville;
        int noCategorie;
        int miseAPrix;

// Récupérer les données du formulaire
      try 
        {
        	nomArticle = request.getParameter("nomArticle");
        	description = request.getParameter("description");
        	noCategorie = Integer.parseInt(request.getParameter("noCategorie"));
        	miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));	
        
        	
        	
        	String debutEncheresString = request.getParameter("dateDebutEnchere");
        	debutEncheres = (debutEncheresString != null) ? java.sql.Date.valueOf(debutEncheresString) : null;
        	
        	String finEnchereString = request.getParameter("dateFinEnchere");
        	finEncheres = (finEnchereString != null) ? java.sql.Date.valueOf(finEnchereString) : null;
        	
        	rue = request.getParameter("rue");
        	codePostal = request.getParameter("code_postal");
        	ville = request.getParameter("ville");
        	
// Créer un objet Article avec les données du formulaire
            ArticleVendu nouvelArticleVendu = new ArticleVendu();
           
            nouvelArticleVendu.setNoCategorie(noCategorie);
            nouvelArticleVendu.setRue(rue);
            nouvelArticleVendu.setCodePostal(codePostal);
            nouvelArticleVendu.setVille(ville);
            nouvelArticleVendu.setNomArticle(nomArticle);
            nouvelArticleVendu.setDescription(description);
            nouvelArticleVendu.setDateDebutEncheres(debutEncheres);
            nouvelArticleVendu.setDateFinEncheres(finEncheres);
            nouvelArticleVendu.setMiseAPrix(miseAPrix);
            nouvelArticleVendu.setNoUtilisateur((int)session.getAttribute("noUtilisateur"));
            	
            
 // Enregistrer l'article dans la base de données en utilisant la  DAL 
            ArticleVenduManager articleVenduManager = new ArticleVenduManager();
			articleVenduManager.addArticleVendu(nouvelArticleVendu);
          //redirection vers la JSP 
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/AfficherVenteArticle.jsp");
			rd.forward(request, response);
        }
//Gestion exceptions
        catch(BusinessException e) 
        {
        	e.printStackTrace();
        }
        

}  		
      
        

	}   