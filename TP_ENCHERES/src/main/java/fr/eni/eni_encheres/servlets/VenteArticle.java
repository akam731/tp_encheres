package fr.eni.eni_encheres.servlets;


import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bll.ArticleVenduManager;
import fr.eni.eni_encheres.bo.ArticleVendu;


/**
 * Servlet implementation class ServletVenteArticle
 */
@WebServlet("/VenteArticle")
public class VenteArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/AfficherVenteArticle.jsp");
		rd.forward(request, response);	
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
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
        	String debutEnchereString = request.getParameter("dateDebutEncheres");
			debutEncheres = java.sql.Date.valueOf(debutEnchereString);
        	String finEnchereString = request.getParameter("dateFinEncheres");
        	finEncheres = java.sql.Date.valueOf(finEnchereString);
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



