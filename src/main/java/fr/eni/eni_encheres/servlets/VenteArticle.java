package fr.eni.eni_encheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bo.ArticleVendu;
import fr.eni.eni_encheres.bo.Categorie;
import fr.eni.eni_encheres.bo.Utilisateur;
import fr.eni.eni_encheres.dal.ArticleVenduDAO;

/**
 * Servlet implementation class ServletVenteArticle
 */
@WebServlet("/VenteArticle")
public class VenteArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AfficherVenteArticle.jsp");
		rd.forward(request, response);	
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		Utilisateur noUtilisateur;
		int noArticle;
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
        	nomArticle = request.getParameter("nom_article");
        	description = request.getParameter("description");
        	noCategorie = Integer.parseInt(request.getParameter("no_categorie"));
        	miseAPrix = Integer.parseInt(request.getParameter("prix_initial"));
        	String debutEnchereString = request.getParameter("date_debut_encheres");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			debutEncheres = LocalDateTime.parse(debutEnchereString, formatter);
        	String finEnchereString = request.getParameter("date_fin_encheres");
        	DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        	finEncheres = LocalDateTime.parse(debutEnchereString, formatter1);
        	rue = request.getParameter("rue");
        	codePostal = request.getParameter("code_postal");
        	ville = request.getParameter("ville");
        	
// Créer un objet Article avec les données du formulaire
            ArticleVendu nouvelArticleVendu = new ArticleVendu();
            nouvelArticleVendu.setNoUtilisateur(noUtilisateur);
            nouvelArticleVendu.setNomArticle(nomArticle);
            nouvelArticleVendu.setDescription(description);
            nouvelArticleVendu.setNoCategorie(noCategorie);
            
        	
// Enregistrer l'article dans la base de données en utilisant la  DAL 
            ArticleVenduDAO articleDAO = new ArticleVenduDAO(); 
            ArticleVenduDAO.enregistrerArticleVendu(nouvelArticleVendu);
            
        }
//Gestion exceptions
        catch(BusinessException e) 
        {
        	e.printStackTrace();
        }
        
//redirection vers la JSP 
		response.sendRedirect("/WEB-INF/jsp/AfficherVenteArticle.jsp");
	}   		
      
        

       
}


