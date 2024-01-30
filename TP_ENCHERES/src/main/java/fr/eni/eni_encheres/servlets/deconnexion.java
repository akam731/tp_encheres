package fr.eni.eni_encheres.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/deconnexion")
public class deconnexion extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate(); // Efface toutes les informations de session
        
		
		// Supression des cookies d'auto connexion
        Cookie userCookie = new Cookie("User", "");
        userCookie.setMaxAge(0);
        response.addCookie(userCookie);
        // Supprimer le cookie "PassWord"
        Cookie passwordCookie = new Cookie("PassWord", "");
        passwordCookie.setMaxAge(0);
        response.addCookie(passwordCookie);
        
        response.sendRedirect("acceuil"); // Redirige vers la page d'accueil
    }
}
