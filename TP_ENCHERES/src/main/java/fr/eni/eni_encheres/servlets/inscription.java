package fr.eni.eni_encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.eni_encheres.dal.DAOFactory;
import fr.eni.eni_encheres.BusinessException;
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
			if(motDePasse.equals(motDePasse2)) {
				
				EncheresDAO dao = (EncheresDAO) DAOFactory.getEncheresDAO();
				Utilisateur utilisateur = new Utilisateur(pseudo,nom,prenom,mail,tel,rue,codePostal,ville,motDePasse,0,false);
				
				try {
					
					dao.setNewUser(utilisateur);
					session.setAttribute("isConnected", true);
					session.setAttribute("pseudo", pseudo);
					session.setAttribute("nom", nom);
					session.setAttribute("prenom", prenom);
					session.setAttribute("mail", mail);
					session.setAttribute("tel", tel);
					session.setAttribute("rue", rue);
					session.setAttribute("codePostal", codePostal);
					session.setAttribute("ville", ville);
					
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else {
				session.setAttribute("errorMdpNotEqual", true); // Créer une variable de session utilisé dans le jsp afin d'aficher
																// l'erreur "mdp différents"
			}
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/inscription.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
