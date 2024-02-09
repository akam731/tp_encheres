package fr.eni.eni_encheres.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;

import fr.eni.eni_encheres.bo.Enchere;


@WebServlet("/listeEnchereConnecte")
public class ListeEnchereConnecte extends HttpServlet {
	private Enchere enchere;
	
	@Override
    public void init() throws ServletException {
        super.init();
        enchere = new Enchere();
    }
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User) request.getSession().getAttribute("user");

        List<Enchere> activeEnchere = Enchere.getAllActiveEnchere();
        List<Enchere> userParticipatedEnchere = Enchere.getUserParticipatedEnchere(currentUser);

        request.setAttribute("activeEnchere", activeEnchere);
        request.setAttribute("userParticipatedEnchere", userParticipatedEnchere);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ListeEnchereConnecte.jsp"); 
        dispatcher.forward(request, response);
    }
}
