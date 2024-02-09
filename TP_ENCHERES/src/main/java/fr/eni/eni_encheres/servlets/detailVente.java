package fr.eni.eni_encheres.servlets;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Date;
import java.time.LocalDate;
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
import fr.eni.eni_encheres.bll.EncheresArticleManager;
import fr.eni.eni_encheres.bll.EniEnchereManager;
import fr.eni.eni_encheres.bll.RetraitManager;
import fr.eni.eni_encheres.bo.ArticleVendu;
import fr.eni.eni_encheres.bo.Categorie;
import fr.eni.eni_encheres.bo.Enchere;
import fr.eni.eni_encheres.bo.Retrait;
import fr.eni.eni_encheres.bo.Utilisateur;
import fr.eni.eni_encheres.dal.EncheresDAO;

@WebServlet("/detailVente")
public class detailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final java.util.Date DATE = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String articleId = request.getParameter("id");

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date date = new java.sql.Date(utilDate.getTime());
		
		if(articleId != null) {
			int Id = 0;
			try {
				Id = Integer.parseInt(articleId);			
				ArticleVenduManager articleDAO = new ArticleVenduManager();
				
				ArticleVendu article = new ArticleVendu();
				
				try {
					article = articleDAO.selectArticleVenduById(Id);
				} catch (BusinessException e) {
					e.printStackTrace();
				}    			
				
				List<String> extensions =new ArrayList<String>();
    			extensions.add(".jpg");
    			extensions.add(".jpeg");
    			extensions.add(".png");
    			extensions.add(".gif");
    			
    			for(String extension:extensions) {    	
    		        File file = new File("C:\\Users\\alexa\\OneDrive\\Documents\\Formation\\ENI\\Git\\tp_encheres\\TP_ENCHERES\\src\\main\\webapp\\img_encheres\\" + articleId + extension);
    				if(file.exists()) {
    					request.setAttribute("articleImg","img_encheres/" + articleId + extension);
    					session.setAttribute("articleImg","img_encheres/" + articleId + extension);
    					break;
    				}else {
    					request.setAttribute("articleImg","img_encheres/default.jpg");
    					session.setAttribute("articleImg","img_encheres/default.jpg");
    				}
    			}
    			
    			//Récupérer le libelle de la catégorie
    			CategorieManager catDAO = new CategorieManager();
    			
    			List<Categorie> listeCategories = catDAO.getListeCategorie();
    			
    			Categorie cat = listeCategories.get(article.getNoCategorie()-1);
    			
    			request.setAttribute("articleCategoryName", cat.getLibelle());
				
    			
    			//Récuperer le retrait
    			RetraitManager retraitDAO = new RetraitManager();
    			Retrait retrait = retraitDAO.selectRetraitById(Id);

    			article.setRue(retrait.getRue());
    			article.setCodePostal(retrait.getCodePostal());
    			article.setVille(retrait.getVille());
    			
    			//Récuperer le vendeur 
    			EniEnchereManager userDAO = new EniEnchereManager();
    			Utilisateur vendeur = userDAO.getUserById(article.getNoUtilisateur());
    			
    			request.setAttribute("articleSeller", vendeur);
    			session.setAttribute("articleSeller", vendeur);
    			
    			//Récuperer la dernière enchère
    			EncheresArticleManager enchereDAO = new EncheresArticleManager();
    			Enchere enchere = new Enchere(-1,-1,DATE ,-1);
    			try{
    				enchere = enchereDAO.getEnchereByArticleId(Id);
    				if(enchere == null) {
    					enchere = new Enchere(-1,-1,DATE ,-1);
    				}
    			}catch (Exception e) {
    				e.printStackTrace();
				}
    			

    			request.setAttribute("enchere", enchere);
    			session.setAttribute("enchereArticleDetail", enchere);
    			
				request.setAttribute("article", article);
				session.setAttribute("articleDetail", article);
				
    			if(session.getAttribute("noUtilisateur") != null && vendeur.getNoUtilisateur() == (int)session.getAttribute("noUtilisateur")) {
    				
    				if(date.before(article.getDateDebutEncheres())) {
	    				response.sendRedirect("modificationEnchere?id=" + Id);
	    				return;
    				}
    			}
			}catch (Exception e) {
				response.sendRedirect("acceuil");
				return;
			}

			
		}else {
			response.sendRedirect("acceuil");
			return;
		}
        
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
						
					}
				} catch (BusinessException e) {
					e.printStackTrace();
				}
			}else {
				response.sendRedirect("connexion");
			}
			
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/detailVente.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String enchere = request.getParameter("enchere");
		int enchereUtilisateur = 0;
		
		try {
			enchereUtilisateur = Integer.parseInt(enchere);
			int miseEnVente = ((ArticleVendu)session.getAttribute("articleDetail")).getMiseAPrix();
			int enchereActuelle = ((ArticleVendu)session.getAttribute("articleDetail")).getPrixVente();
			if(enchereUtilisateur >= miseEnVente) {
				if(enchereUtilisateur > enchereActuelle || enchereActuelle == 0) {
					if((int)session.getAttribute("credits") >= enchereUtilisateur) {
						
						EncheresArticleManager enchereDeo = new EncheresArticleManager();
						EniEnchereManager utilisateurDAO = new EniEnchereManager();
						ArticleVenduManager articleDAO = new ArticleVenduManager();
						
						Enchere derniereEnchere = (Enchere) session.getAttribute("enchereArticleDetail");
						int nouveauSolde = (int)session.getAttribute("credits") - enchereUtilisateur;

						LocalDate localDate = LocalDate.now();
						Date date = Date.valueOf(localDate);
						/*
				        java.util.Date utilDate = new java.util.Date();
				        java.sql.Date date = new java.sql.Date(utilDate.getTime());
				        */
						if(derniereEnchere.getNo_Article() != -1) {

							//Remboursement de l'ancien encherisseur
							
							int idEncherisseur = derniereEnchere.getNo_Utilisateur();
							Utilisateur encherisseur = utilisateurDAO.getUserById(idEncherisseur);
							encherisseur.setCredit(encherisseur.getCredit() + enchereActuelle);
							
							utilisateurDAO.editUserById(
									encherisseur.getPseudo(),
									encherisseur.getNom(), 
									encherisseur.getPrenom(),
									encherisseur.getEmail(), 
									encherisseur.getTelephone(),
									encherisseur.getRue(), 
									encherisseur.getCodePostal(), 
									encherisseur.getVille(), 
									encherisseur.getMotDePasse(), 
									encherisseur.getCredit(), 
									encherisseur.isAdministrateur(), 
									encherisseur.getNoUtilisateur());
							
							if(encherisseur.getNoUtilisateur() == (int)session.getAttribute("noUtilisateur")) {
								session.setAttribute("credits", encherisseur.getCredit());
							}
							
							//Modification de l'enchere
							
							int encherisseurId = derniereEnchere.getNo_Utilisateur();
							derniereEnchere.setMontantEnchere(enchereUtilisateur);
							derniereEnchere.setDateEnchere(date);
							derniereEnchere.setNo_Utilisateur((int)session.getAttribute("noUtilisateur"));
							
							enchereDeo.editEcnhereById(derniereEnchere, encherisseurId, derniereEnchere.getNo_Article());
							

			    			request.setAttribute("enchere", derniereEnchere);
			    			session.setAttribute("enchereArticleDetail", derniereEnchere);
			    			
						}else {
							//Création de l'enchère
							Enchere nouvelleEnchere = new Enchere((int)session.getAttribute("noUtilisateur"),((ArticleVendu)session.getAttribute("articleDetail")).getNoArticle() ,date,enchereUtilisateur);
							enchereDeo.setNewEnchere(nouvelleEnchere);
							

			    			request.setAttribute("enchere", nouvelleEnchere);
			    			session.setAttribute("enchereArticleDetail", nouvelleEnchere);	

						}
						
		    			//Mise à jour du prix de vente de l'enchère
						ArticleVendu articleUpdate = (ArticleVendu)session.getAttribute("articleDetail");
						articleUpdate.setPrixVente(enchereUtilisateur);

						articleDAO.updateArticleVendu(articleUpdate);
						
						
						request.setAttribute("article", articleUpdate);
						session.setAttribute("articleDetail", articleUpdate);
						
						//Débit de l'utilisateur
						utilisateurDAO.editUserById((String)session.getAttribute("pseudo"),
													(String)session.getAttribute("nom"), 
													(String)session.getAttribute("prenom"),
													(String)session.getAttribute("mail"), 
													(String)session.getAttribute("tel"),
													(String)session.getAttribute("rue"), 
													(String)session.getAttribute("codePostal"), 
													(String)session.getAttribute("ville"), 
													(String)session.getAttribute("mdp"), 
													nouveauSolde, 
													(Boolean)session.getAttribute("admin"), 
													(int)session.getAttribute("noUtilisateur"));

						session.setAttribute("credits", nouveauSolde);
					}else {
						request.setAttribute("erreurDetailEnchere", "Votre solde de crédits est insufisant pour faire cette enchère !");
					}
				}else {
					request.setAttribute("erreurDetailEnchere", "Votre enchère doit être suppérieur à l'offre actuelle !");
				}				
			}else {
				request.setAttribute("erreurDetailEnchere", "Votre enchère doit être suppérieur à la mise à prix !");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erreurDetailEnchere", "Veuillez remplir une valeur numérique !");
		}
		
		
		doGet(request, response);
	}

}