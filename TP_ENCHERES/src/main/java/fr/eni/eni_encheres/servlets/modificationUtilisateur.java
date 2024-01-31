package fr.eni.eni_encheres.servlets;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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

/**
 * Servlet implementation class modificationUtilisateur
 */
@WebServlet("/modificationUtilisateur")
public class modificationUtilisateur extends HttpServlet {
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
					RequestDispatcher rs = request.getRequestDispatcher("jsp/modificationUtilisateur.jsp");
					rs.forward(request, response);
				}
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}else {
			if(session.getAttribute("pseudo") == null) {
				response.sendRedirect("connexion");
			}else {
				RequestDispatcher rs = request.getRequestDispatcher("jsp/modificationUtilisateur.jsp");
				rs.forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if("Suppimer mon compte".equals(request.getParameter("suppimer"))) {
			if(request.getParameter("motDePasse").equals(session.getAttribute("mdp"))) {
				EniEnchereManager user = new EniEnchereManager();
				try {
					user.deleteUserById((int)session.getAttribute("noUtilisateur"));
					response.sendRedirect("deconnexion");
				} catch (BusinessException e) {
					e.printStackTrace();
				}
			}else {
				request.setAttribute("erreurModif", "Vous devez saisir votre mot de passe actuel pour supprimer votre compte !");
			}
		}
		
		if("Enregistrer".equals(request.getParameter("enregistrer"))) {
			
			List<String> valeurs = new ArrayList<String>();
			
			String pseudo = (String) request.getParameter("pseudo"); 
			valeurs.add(pseudo);
			String nom = (String) request.getParameter("nom");
			valeurs.add(nom);
			String prenom = (String) request.getParameter("prenom");
			valeurs.add(prenom);
			String mail = (String) request.getParameter("email");
			valeurs.add(mail);
			String tel = (String) request.getParameter("telephone");
			valeurs.add(tel);
			String rue = (String) request.getParameter("rue");
			valeurs.add(rue);
			String codePostal = (String) request.getParameter("codePostal");
			valeurs.add(codePostal);
			String ville = (String) request.getParameter("ville");
			valeurs.add(ville);
			String motDePasse = (String) request.getParameter("motDePasse");
			valeurs.add(motDePasse);
			
			Boolean notNull = true;
			
			for(int i = 0; i < valeurs.size(); i++) {
				if(valeurs.get(i) == null) {
					notNull = false;
				}
			}

			EniEnchereManager dao = new EniEnchereManager();
			if(notNull) {
				try {
				if(motDePasse.equals(session.getAttribute("mdp"))) {
					
					if(!dao.isColloneExiste("pseudo", pseudo) || pseudo.equals(session.getAttribute("pseudo"))) {
						if(!dao.isColloneExiste("email", mail) || mail.equals(session.getAttribute("mail"))) {						
							if((request.getParameter("motDePasse2") != null && !request.getParameter("motDePasse2").isEmpty()) || (request.getParameter("motDePasse3") != null && !request.getParameter("motDePasse3").isEmpty())) {
								if(request.getParameter("motDePasse2").equals(request.getParameter("motDePasse3"))) {
									
									
									motDePasse = request.getParameter("motDePasse2");
									
								}else {
									pseudo = null;
									request.setAttribute("errorInscription", " Vos nouveaux mots de passes ne sont pas égaux !"); 
									RequestDispatcher rd = request.getRequestDispatcher("/jsp/inscription.jsp");
									rd.forward(request, response);
								}
			
							}
							Utilisateur editedUser = new Utilisateur();
							try {
								
								editedUser = dao.editUserById(pseudo, nom, prenom, mail, tel, rue, codePostal, ville, motDePasse, (int)session.getAttribute("credits"), (Boolean)session.getAttribute("admin"), (int)session.getAttribute("noUtilisateur"));
							
							
							} catch (BusinessException e) {
								e.printStackTrace();
								request.setAttribute("errorInscription", "Veuillez respecter le format de chaque champs !"); 
								RequestDispatcher rd = request.getRequestDispatcher("/jsp/inscription.jsp");
								rd.forward(request, response);
							} 
							
							if(!editedUser.getPseudo().equals("ERREUR_WRONG_FORMAT")) {
								
								session.setAttribute("isConnected", true);
								session.setAttribute("pseudo", editedUser.getPseudo());
								session.setAttribute("nom", editedUser.getNom());
								session.setAttribute("prenom", editedUser.getPrenom());
								session.setAttribute("mail", editedUser.getEmail());
								session.setAttribute("tel", editedUser.getTelephone());
								session.setAttribute("rue", editedUser.getRue());
								session.setAttribute("codePostal", editedUser.getCodePostal());
								session.setAttribute("ville", editedUser.getVille());
								session.setAttribute("mdp", editedUser.getMotDePasse());
								
								System.out.println(editedUser.getMotDePasse());
			
								Cookie[] cookies = request.getCookies();
								
								if(cookies != null) {
									for(Cookie cookie : cookies) {
										if("User".equals(cookie.getName())) {
								        	Cookie userPseudo = new Cookie("User", URLEncoder.encode(pseudo, "UTF-8"));		
								        	userPseudo.setMaxAge(365 * 24 * 60 * 60);
								        	response.addCookie(userPseudo);
										}
										if("PassWord".equals(cookie.getName())) {
								        	Cookie userMdp = new Cookie("PassWord", URLEncoder.encode(motDePasse, "UTF-8"));
								        	userMdp.setMaxAge(365 * 24 * 60 * 60);
								        	response.addCookie(userMdp);;
										}
									}
								}
								
			
								RequestDispatcher rd = request.getRequestDispatcher("jsp/modificationUtilisateur.jsp");
								rd.forward(request, response);
							}else {
								request.setAttribute("erreurModif"," Veuillez respecter le format de chaque champs de saisis !");
								RequestDispatcher rd = request.getRequestDispatcher("jsp/modificationUtilisateur.jsp");
								rd.forward(request, response);
							}
						}else {
							request.setAttribute("erreurModif"," L'adresse email saisis existe déja !");
							RequestDispatcher rd = request.getRequestDispatcher("jsp/modificationUtilisateur.jsp");
							rd.forward(request, response);
						}
					}else {
						request.setAttribute("erreurModif"," Le pseudo saisis existe déja !");
						RequestDispatcher rd = request.getRequestDispatcher("jsp/modificationUtilisateur.jsp");
						rd.forward(request, response);
					}
				}else {
					request.setAttribute("erreurModif","Mot de passe incorrect !");
					RequestDispatcher rd = request.getRequestDispatcher("jsp/modificationUtilisateur.jsp");
					rd.forward(request, response);
				}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				request.setAttribute("erreurModif","Vous devez remplir tous les champs obligatoires !");
				RequestDispatcher rd = request.getRequestDispatcher("jsp/modificationUtilisateur.jsp");
				rd.forward(request, response);
			}
			
		}
	}

}
