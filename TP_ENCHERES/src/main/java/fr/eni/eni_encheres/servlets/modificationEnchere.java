package fr.eni.eni_encheres.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bll.ArticleVenduManager;
import fr.eni.eni_encheres.bll.CategorieManager;
import fr.eni.eni_encheres.bll.EniEnchereManager;
import fr.eni.eni_encheres.bll.RetraitManager;
import fr.eni.eni_encheres.bo.ArticleVendu;
import fr.eni.eni_encheres.bo.Categorie;
import fr.eni.eni_encheres.bo.Enchere;
import fr.eni.eni_encheres.bo.Retrait;
import fr.eni.eni_encheres.bo.Utilisateur;

@WebServlet("/modificationEnchere")

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
                 maxFileSize = 1024 * 1024 * 10,      // 10 MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50 MB
public class modificationEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Fonction pour récuperer le nom de l'image importé
    private String getSubmittedFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date date = new java.sql.Date(utilDate.getTime());
	       
        
		String articleId = request.getParameter("id");

        CategorieManager catManager;
        try {
            catManager = new CategorieManager();
            List<Categorie> listeCategories = catManager.getListeCategorie();
            request.setAttribute("categories", listeCategories);
        } catch (BusinessException e1) {
            e1.printStackTrace();
        }
        
		int Id = 0;
		if(articleId != null) {
			try {
				Id = Integer.parseInt(articleId);
			}catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("connexion");
				return;
			}
		}else {
			response.sendRedirect("connexion");
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
				return;
			}
			
		}else {
			
			ArticleVendu article = (ArticleVendu)session.getAttribute("articleDetail"); 
			Utilisateur vendeur = (Utilisateur)session.getAttribute("articleSeller");
			if(vendeur.getNoUtilisateur() == (int)session.getAttribute("noUtilisateur")) {
				if(article.getDateDebutEncheres().before(date)) {
					response.sendRedirect("detailVente?id=" + Id);
					return;
				}
				
			}else {
				System.out.println("zizi3");
				response.sendRedirect("acceuil");
				return;
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/modificationEnchere.jsp");
			rd.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if("Enregistrer".equals(request.getParameter("enregistrer"))) {

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
	        	noCategorie = Integer.parseInt(request.getParameter("categorie"));
	        	miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));	
	        

				Part part = request.getPart("image_article");
				String originalFileName = getSubmittedFileName(part);
				Boolean isImg = false;
				
				
				// Récupère l'extension de l'image d'origine
				String fileExtension = "";
				int dotIndex = originalFileName.lastIndexOf('.');
				if (dotIndex > 0) {
				    fileExtension = originalFileName.substring(dotIndex).toLowerCase();
				}
				
				
				
	        	String debutEncheresString = request.getParameter("dateDebutEnchere");
	        	debutEncheres = (debutEncheresString != null) ? java.sql.Date.valueOf(debutEncheresString) : null;
	        	
	        	String finEnchereString = request.getParameter("dateFinEnchere");
	        	finEncheres = (finEnchereString != null) ? java.sql.Date.valueOf(finEnchereString) : null;
	        	
	        	rue = request.getParameter("rue");
	        	codePostal = request.getParameter("codePostal");
	        	ville = request.getParameter("ville");
	        	
	        	
	        	if (!"".equals(originalFileName)) {

	    			List<String> acceptedExtensions =new ArrayList<String>();
	    			acceptedExtensions.add(".jpg");
	    			acceptedExtensions.add(".jpeg");
	    			acceptedExtensions.add(".png");
	    			acceptedExtensions.add(".gif");
	    			
	    			Boolean isExtensionAccepted = false;
	    			
	    			for(String extension:acceptedExtensions) {    				
	    				if(fileExtension.equals(extension)) {
	    					isExtensionAccepted = true;
	    				}
	    			}
	    			
	    			if (!isExtensionAccepted) {
						request.setAttribute("errorModif", "Les format d'images acceptés sont : JPG, JPEG, PNG et GIF !");
						session.setAttribute("errorModif", "Les format d'images acceptés sont : JPG, JPEG, PNG et GIF !");
						RequestDispatcher rd = request.getRequestDispatcher("/jsp/AfficherVenteArticle.jsp");
						rd.forward(request, response);
		        		return;
					}

	    			isImg = true;
	        	}

	            java.util.Date utilDate = new java.util.Date();
	            java.sql.Date date = new java.sql.Date(utilDate.getTime());
	        	if(date.before(debutEncheres)) {
		        	
		        	if(debutEncheres.before(finEncheres)) {
		        		ArticleVendu article = (ArticleVendu)session.getAttribute("articleDetail"); 
		        		
			            ArticleVenduManager articleVenduManager = new ArticleVenduManager();
			            ArticleVendu nouvelArticleVendu = new ArticleVendu(article.getNoArticle(),nomArticle,  description,  debutEncheres,  finEncheres, article.getPrixVente(),miseAPrix ,  (int)session.getAttribute("noUtilisateur"),  noCategorie);
			            nouvelArticleVendu.setCodePostal(codePostal);
			            nouvelArticleVendu.setVille(ville);
			            nouvelArticleVendu.setRue(rue);
			            articleVenduManager.updateArticleVendu(nouvelArticleVendu);
			            
			            if(!nouvelArticleVendu.getNomArticle().equals("ERREUR_WRONG_FORMAT")) {
		
				            RetraitManager retDAO = new RetraitManager();
			            	Retrait retrait = new Retrait(nouvelArticleVendu.getNoArticle(), rue, codePostal, ville);
			            	retDAO.updateRetraitById(retrait);
		
			            	if(!retrait.getVille().equals("ERREUR_WRONG_FORMAT")) {
			            		
			            		if(isImg) {
				            		try {
				            		
				            			// Récupère le numéro de l'article
				            			String fixedFileName = String.valueOf(nouvelArticleVendu.getNoArticle());

			
				            			// Concatène le nom avec l'extension du fichier d'origine
				            			String fileName = fixedFileName + fileExtension;

				            			// Spécifiez le chemin absolu du répertoire en dehors du Workspace
				            			String uploadPath = "C:\\Users\\alexa\\OneDrive\\Documents\\Formation\\ENI\\Git\\tp_encheres\\TP_ENCHERES\\src\\main\\webapp\\img_encheres";
        								Path test1 = Paths.get(uploadPath+ "\\" + nouvelArticleVendu.getNoArticle()+".jpg");
        								File fileTest1 = new File(uploadPath+ "\\" + nouvelArticleVendu.getNoArticle()+".jpg");
        								Path test2 = Paths.get(uploadPath+ "\\" + nouvelArticleVendu.getNoArticle()+".png");    
        								File fileTest2 = new File(uploadPath+ "\\" + nouvelArticleVendu.getNoArticle()+".png");
        								Path test3 = Paths.get(uploadPath+ "\\" + nouvelArticleVendu.getNoArticle()+"jpeg");    
        								File fileTest3 = new File(uploadPath+ "\\" + nouvelArticleVendu.getNoArticle()+".jpeg");
        								Path test4 = Paths.get(uploadPath+ "\\" + nouvelArticleVendu.getNoArticle()+".gif");           								
        								File fileTest4 = new File(uploadPath+ "\\" + nouvelArticleVendu.getNoArticle()+".gif");
        								if(Files.exists(test1)) {
        									fileTest1.delete();
				            			}else if(Files.exists(test2)) {
        									fileTest2.delete();
				            			}else if(Files.exists(test3)) {
        									fileTest3.delete();
				            			}else if(Files.exists(test4)) {
        									fileTest4.delete();
				            			}
        								
				            			
				            			Path filePath = Paths.get(uploadPath, fileName);

				            			// Assurez-vous que le répertoire img_encheres existe
				            			if (!Files.exists(filePath.getParent())) {
				            			    Files.createDirectories(filePath.getParent());
				            			}

				            			try (InputStream input = part.getInputStream()) {
				            			    Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);
				            			} catch (IOException e) {
				            			    e.printStackTrace();
				            			}
				                        
				                        
				            		}catch (Exception e) {
				            			
				            			e.printStackTrace();
				            		
									}
			            		} 
			            		session.setAttribute("articleDetail", nouvelArticleVendu);
			            		session.setAttribute("enchereArticleDetail", nouvelArticleVendu);
				            	
								response.sendRedirect("modificationEnchere?id="+nouvelArticleVendu.getNoArticle());
			            		
			            	}else {
								request.setAttribute("errorModif", "Vous devez respecter le format de tous les champs !");
								RequestDispatcher rd = request.getRequestDispatcher("/jsp/modificationEnchere.jsp");
								rd.forward(request, response);
			            	}
			            }else {
							request.setAttribute("errorModif", "Vous devez respecter le format de tous les champs !");
							RequestDispatcher rd = request.getRequestDispatcher("/jsp/modificationEnchere.jsp");
							rd.forward(request, response);
			            }
		        	}else {
						request.setAttribute("errorModif", "La date de fin ne peux pas être antérieur à la date de début !");
						RequestDispatcher rd = request.getRequestDispatcher("/jsp/modificationEnchere.jsp");
						rd.forward(request, response);
		            }
	        	}else {
					request.setAttribute("errorModif", "La date de début ne peux pas être antérieur à la date actuelle !");
					RequestDispatcher rd = request.getRequestDispatcher("/jsp/modificationEnchere.jsp");
					rd.forward(request, response);
	        	}
	          

	        }catch(BusinessException e) {
	        	e.printStackTrace();
	        }
			
			
		}else if("Annuler la vente".equals(request.getParameter("annulerVente"))) {

    		ArticleVendu article = (ArticleVendu)session.getAttribute("articleDetail"); 
    		
    		ArticleVenduManager articleVenduManager = new ArticleVenduManager();
    		RetraitManager retraitDAO = new RetraitManager();
			

            java.util.Date utilDate = new java.util.Date();
            java.sql.Date date = new java.sql.Date(utilDate.getTime());

            if(date.before(article.getDateDebutEncheres())) {
            	
            	int idArticle = article.getNoArticle();
            	
            	try {
					retraitDAO.deleteRetrait(idArticle);
					articleVenduManager.deleteArticleVendu(idArticle);
					
        			String uploadPath = "C:\\Users\\alexa\\OneDrive\\Documents\\Formation\\ENI\\Git\\tp_encheres\\TP_ENCHERES\\src\\main\\webapp\\img_encheres";
					Path test1 = Paths.get(uploadPath+ "\\" + idArticle +".jpg");
					File fileTest1 = new File(uploadPath+ "\\" + idArticle+".jpg");
					Path test2 = Paths.get(uploadPath+ "\\" + idArticle+".png");    
					File fileTest2 = new File(uploadPath+ "\\" + idArticle+".png");
					Path test3 = Paths.get(uploadPath+ "\\" + idArticle+"jpeg");    
					File fileTest3 = new File(uploadPath+ "\\" + idArticle+".jpeg");
					Path test4 = Paths.get(uploadPath+ "\\" + idArticle+".gif");           								
					File fileTest4 = new File(uploadPath+ "\\" + idArticle+".gif");
					if(Files.exists(test1)) {
						fileTest1.delete();
        			}else if(Files.exists(test2)) {
						fileTest2.delete();
        			}else if(Files.exists(test3)) {
						fileTest3.delete();
        			}else if(Files.exists(test4)) {
						fileTest4.delete();
        			}

					response.sendRedirect("acceuil");
					
				} catch (BusinessException e) {
					e.printStackTrace();
				}
            	
            }else {
				request.setAttribute("errorModif", "Votre enchère à déjà commencé !");
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/modificationEnchere.jsp");
				rd.forward(request, response);
            }
			
		}
		
	}

}
