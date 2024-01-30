package fr.eni.eni_encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
		RequestDispatcher rs = request.getRequestDispatcher("jsp/modificationUtilisateur.jsp");
		rs.forward(request, response);
		
		
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
	}

}
