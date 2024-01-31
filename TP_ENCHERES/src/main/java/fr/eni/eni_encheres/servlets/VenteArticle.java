package fr.eni.eni_encheres.servlets;


import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bll.ArticleVenduManager;
import fr.eni.eni_encheres.bo.ArticleVendu;
import fr.eni.eni_encheres.bo.Categorie;
import fr.eni.eni_encheres.bo.Retrait;
import fr.eni.eni_encheres.bo.Utilisateur;


/**
 * Servlet implementation class ServletVenteArticle
 */
@WebServlet("/VenteArticle")
public class VenteArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/AfficherVenteArticle.jsp");
		rd.forward(request, response);	
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		
        String nomArticle; 
        String description; 
        int noCategorie;
        int miseAPrix; 
        LocalDateTime debutEncheres;
        LocalDateTime finEncheres;
        String rue;
        String codePostal;
        String ville;
        
// Récupérer les données du formulaire
        try 
        {
        	nomArticle = request.getParameter("nomArticle");
        	description = request.getParameter("description");
        	noCategorie = Integer.parseInt(request.getParameter("noCategorie"));
        	miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));	
        	String debutEnchereString = request.getParameter("dateDebutEncheres");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			debutEncheres = LocalDateTime.parse(debutEnchereString, formatter);
        	String finEnchereString = request.getParameter("dateFinEncheres");
        	DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        	finEncheres = LocalDateTime.parse(finEnchereString, formatter1);
        	rue = request.getParameter("rue");
        	codePostal = request.getParameter("code_postal");
        	ville = request.getParameter("ville");
        	
// Créer un objet Article avec les données du formulaire
            ArticleVendu nouvelArticleVendu = new ArticleVendu();
            Categorie categorie = new Categorie();
            Retrait retrait = new Retrait();
            categorie.setNoCategorie(noCategorie);
            retrait.setRue(rue);
            retrait.setCodePostal(codePostal);
            retrait.setVille(ville);
            nouvelArticleVendu.setNomArticle(nomArticle);
            nouvelArticleVendu.setDescription(description);
            nouvelArticleVendu.setDateDebutEncheres(debutEncheres);
            nouvelArticleVendu.setDateFinEncheres(finEncheres);
            nouvelArticleVendu.setMiseAPrix(miseAPrix);
            
        	
// Enregistrer l'article dans la base de données en utilisant la  DAL 
            ArticleVenduManager articleVenduManager = new ArticleVenduManager();
			articleVenduManager.enregistrerArticleVendu(nouvelArticleVendu, retrait, categorie);
          //redirection vers la JSP 
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/AfficherVenteArticle.jsp");
			rd.forward(request, response);
        }
//Gestion exceptions
        catch(BusinessException e) 
        {
        	e.printStackTrace();
        }
        

	}   		
      
        

       
}


