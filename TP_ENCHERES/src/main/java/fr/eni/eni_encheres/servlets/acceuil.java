package fr.eni.eni_encheres.servlets;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bll.ArticleVenduManager;
import fr.eni.eni_encheres.bll.CategorieManager;
import fr.eni.eni_encheres.bll.EniEnchereManager;
import fr.eni.eni_encheres.bo.ArticleVendu;
import fr.eni.eni_encheres.bo.Categorie;
import fr.eni.eni_encheres.bo.Utilisateur;

@WebServlet("/acceuil")
public class acceuil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		/* Liste des catégories dynamiques (@author Amélie DUCASSE) */
        CategorieManager catManager;
        try {
            catManager = new CategorieManager();
            List<Categorie> listeCategories = catManager.getListeCategorie();
            request.setAttribute("categories", listeCategories);
        } catch (BusinessException e1) {
            e1.printStackTrace();
        }
        request.setAttribute("categorieInvoker", "acceuil");
        
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
						
					}
				} catch (BusinessException e) {
					e.printStackTrace();
				}
			}
		}

		
		ArticleVenduManager articleDAO = new ArticleVenduManager();
		EniEnchereManager utilisateurDAO = new EniEnchereManager();
		List<ArticleVendu> listesArticles = new ArrayList<ArticleVendu>();
		Map<String , String> dicUser = new HashMap<>();
		Map<String , String> articlesImg = new HashMap<>();
		

			try {
				listesArticles = articleDAO.selectAllArticleVendu();
				
				
				for (ArticleVendu article : listesArticles) {
				    
				    Utilisateur user = utilisateurDAO.getUserById(article.getNoUtilisateur()); 
				    dicUser.put(String.valueOf(article.getNoUtilisateur()), String.valueOf(user.getPseudo()));
				    
				    String articleId = String.valueOf(article.getNoArticle());
				    
	    			List<String> extensions =new ArrayList<String>();
	    			extensions.add(".jpg");
	    			extensions.add(".jpeg");
	    			extensions.add(".png");
	    			extensions.add(".gif");
	    			extensions.add(".JPG");
	    			extensions.add(".JPEG");
	    			extensions.add(".PNG");
	    			extensions.add(".GIF");
	    			
	    			for(String extension:extensions) {    	
	    				
	    		        File file = new File("C:\\Users\\alexa\\OneDrive\\Documents\\Formation\\ENI\\Git\\tp_encheres\\TP_ENCHERES\\src\\main\\webapp\\img_encheres\\" + articleId + extension);
	    				if(file.exists()) {
	    					articlesImg.put(String.valueOf(article.getNoArticle()), "img_encheres/" + articleId + extension);
	    					break;
	    				}else {
	    					articlesImg.put(String.valueOf(article.getNoArticle()), "img_encheres/default.jpg");
	    				}
	    			}
				    
				}
				
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		
			request.setAttribute("liste", listesArticles);
		    request.setAttribute("dicUser", dicUser);
		    request.setAttribute("articlesImg", articlesImg);
		    
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/acceuil.jsp");
		rd.forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			/* Liste des catégories dynamiques (@author Amélie DUCASSE) */
	        CategorieManager catManager;
	        try {
	            catManager = new CategorieManager();
	            List<Categorie> listeCategories = catManager.getListeCategorie();
	            request.setAttribute("categories", listeCategories);
	        } catch (BusinessException e1) {
	            e1.printStackTrace();
	        }
	        request.setAttribute("categorieInvoker", "acceuil");
        
			String recherche = request.getParameter("rechercher");
			String categorie = request.getParameter("categorie");
			
			ArticleVenduManager articleDAO = new ArticleVenduManager();
			EniEnchereManager utilisateurDAO = new EniEnchereManager();
			List<ArticleVendu> listesArticles = new ArrayList<ArticleVendu>();
			Map<String , String> dicUser = new HashMap<>();
			Map<String , String> articlesImg = new HashMap<>();
			
			try {
			    listesArticles = articleDAO.selectAllArticleVendu();

			    if (!categorie.equals("toutes")) {
			        Iterator<ArticleVendu> iterator = listesArticles.iterator();
			        while (iterator.hasNext()) {
			            ArticleVendu article = iterator.next();
			            if (!String.valueOf(article.getNoCategorie()).equals(categorie)) {
			                iterator.remove();
			            }
			        }
			    }


			} catch (BusinessException e) {
			    e.printStackTrace();
			}

			if (!recherche.equals("")) {
		        Iterator<ArticleVendu> iterator = listesArticles.iterator();
		        while (iterator.hasNext()) {
		            ArticleVendu article = iterator.next();
		            if(!article.getNomArticle().contains(recherche)) {
		                iterator.remove();
		            }
		        }
				
			}
			
			
			for (ArticleVendu article : listesArticles) {
			    
			    Utilisateur user = null;
				try {
					user = utilisateurDAO.getUserById(article.getNoUtilisateur());
				} catch (BusinessException e) {
					e.printStackTrace();
				} 
			    dicUser.put(String.valueOf(article.getNoUtilisateur()), String.valueOf(user.getPseudo()));
			    
			    String articleId = String.valueOf(article.getNoArticle());
			    
    			List<String> extensions =new ArrayList<String>();
    			extensions.add(".jpg");
    			extensions.add(".jpeg");
    			extensions.add(".png");
    			extensions.add(".gif");
    			
    			for(String extension:extensions) {    	
    		        File file = new File("C:\\Users\\alexa\\OneDrive\\Documents\\Formation\\ENI\\Git\\tp_encheres\\TP_ENCHERES\\src\\main\\webapp\\img_encheres\\" + articleId + extension);
    				if(file.exists()) {
    					articlesImg.put(String.valueOf(article.getNoArticle()), "img_encheres/" + articleId + extension);
    					break;
    				}else {
    					articlesImg.put(String.valueOf(article.getNoArticle()), "img_encheres/default.jpg");
    				}
    			}
			    
			}
			
			request.setAttribute("liste", listesArticles);
		    request.setAttribute("dicUser", dicUser);
		    request.setAttribute("articlesImg", articlesImg);
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/acceuil.jsp");
		rd.forward(request, response);
	}

}