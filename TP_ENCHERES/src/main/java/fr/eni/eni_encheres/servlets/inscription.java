package fr.eni.eni_encheres.servlets;

import java.io.IOException;
import java.net.URLDecoder;
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

import fr.eni.eni_encheres.dal.DAOFactory;
import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bll.EniEnchereManager;
import fr.eni.eni_encheres.bo.Utilisateur;
import fr.eni.eni_encheres.dal.EncheresDAO;

/**
 * Servlet implementation class inscription
 */
@WebServlet("/inscription")
public class inscription extends HttpServlet {
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
					RequestDispatcher rd = request.getRequestDispatcher("/jsp/inscription.jsp");
					rd.forward(request, response);
				}
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}else {
			if(session.getAttribute("pseudo") != null) {
				response.sendRedirect("acceuil");
			}else {
				RequestDispatcher rs = request.getRequestDispatcher("jsp/inscription.jsp");
				rs.forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
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
		String motDePasse2 = (String) request.getParameter("motDePasse2");
		valeurs.add(motDePasse2);
		
		Boolean notNull = true;
		
		for(int i = 0; i < valeurs.size(); i++) {
			if(valeurs.get(i) == null) {
				notNull = false;
			}
		}
		
		if(notNull) {
			EniEnchereManager exist = new EniEnchereManager();
			
			try {
				if(!exist.isColloneExiste("pseudo", pseudo)) {
					if(!exist.isColloneExiste("email", mail)) {	
						if(codePostal.length() < 10) {	
							if(motDePasse.equals(motDePasse2)) {
								
								
								EniEnchereManager utilisateur = new EniEnchereManager();
								
								try {
									Utilisateur user = utilisateur.setNewUser(pseudo,nom,prenom,mail,tel,rue,codePostal,ville,motDePasse);

									if(!user.getPseudo().equals("ERREUR_WRONG_FORMAT")) {
										
										session.setAttribute("isConnected", true);
										session.setAttribute("noUtilisateur", user.getNoUtilisateur());
										session.setAttribute("pseudo", pseudo);
										session.setAttribute("nom", nom);
										session.setAttribute("prenom", prenom);
										session.setAttribute("mail", mail);
										session.setAttribute("tel", tel);
										session.setAttribute("rue", rue);
										session.setAttribute("codePostal", codePostal);
										session.setAttribute("ville", ville);
										session.setAttribute("mdp", motDePasse);
										session.setAttribute("admin", user.isAdministrateur());
										session.setAttribute("credits", user.getCredit());
										
										response.sendRedirect("acceuil");
										
									}else {
										request.setAttribute("errorInscription", "Veuillez respecter le format de chaque champs de saisis !"); 
										RequestDispatcher rd = request.getRequestDispatcher("/jsp/inscription.jsp");
										rd.forward(request, response);
									}
								} catch (BusinessException e) {
									request.setAttribute("errorInscription", "Veuillez respecter le format de chaque champs !"); 
									RequestDispatcher rd = request.getRequestDispatcher("/jsp/inscription.jsp");
									rd.forward(request, response);
									e.printStackTrace();
								}
								
							}else {
								request.setAttribute("errorInscription", "Vos mots de passe doivent être égaux !"); 
								RequestDispatcher rd = request.getRequestDispatcher("/jsp/inscription.jsp");
								rd.forward(request, response);
							}
						}else {
							request.setAttribute("errorInscription", "Votre Code postal doit avoir au plus 10 caractères !");
							RequestDispatcher rd = request.getRequestDispatcher("/jsp/inscription.jsp");
							rd.forward(request, response);
						}
					}else {
						request.setAttribute("errorInscription", "Votre E-mail est déja prise !");
						RequestDispatcher rd = request.getRequestDispatcher("/jsp/inscription.jsp");
						rd.forward(request, response);
					}
				}else {
					request.setAttribute("errorInscription", "Votre pseudo est déja pris !");
					RequestDispatcher rd = request.getRequestDispatcher("/jsp/inscription.jsp");
					rd.forward(request, response);
				}
			} catch (BusinessException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			request.setAttribute("errorInscription", "Tous les champs doivent être remplis !");
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/inscription.jsp");
			rd.forward(request, response);
		}
		
		
	}

}