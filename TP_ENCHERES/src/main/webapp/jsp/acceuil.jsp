<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="fr.eni.eni_encheres.bo.ArticleVendu" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Acceuil</title>
	<link href="css/acceuil.css" rel="stylesheet">
</head>
<body>
	<header class="acceuil_header">
    	<h2>ENI - Enchères | Accueil</h2>
    	<nav>
    	<% 
    	
    	String userId = (String) session.getAttribute("pseudo");
    	
    	if(userId != null){
    		%>
    		<a href="">Enchères</a>
    		<a href="VenteArticle">Vendre un article</a>
    		<a href="utilisateur?username=<%= session.getAttribute("pseudo") %>">Mon profil</a>
    		<a href="deconnexion">Déconnexion</a>
    		
    		<% 
    	}else{
    		%>
    		
    		<a href="connexion">S'inscrire - Se connecter</a>
    		
    		<%
    	}
    	
    	%>
    	
    	</nav>
    </header>

    <h1>Liste des enchères</h1>

    <h3>Filtres</h3>
    <form method="post" action="/TP_ENCHERES/acceuil">
    	<div class="form_flex">
    		<div class="recherche">
    			<div class="img_loupe">
    				<img id="loupe" src="images/loupe.png">
    			</div>
    			<input id="barre_recherche" type="text" name="rechercher" placeholder="Le nom de l'article contient">
    		</div>
    		<br>
	
		<label class="label_bottom" for="category">Catégorie :</label>
		<%@ include file="/jsp/fragmentListeCategorie.jsp" %>
    	</div>

    	<input class="rechercher_bouton" type="submit" name="rechercher" value="Rechercher">
    </form>

       <section class="afficher_encheres">
        <%
            Map<String, String> dicUser = (HashMap<String, String>) request.getAttribute("dicUser");
       		Map<String, String> articlesImg = (HashMap<String, String>) request.getAttribute("articlesImg");

            if (dicUser != null) {
                List<ArticleVendu> liste = (List<ArticleVendu>) request.getAttribute("liste");
                if (liste != null && !liste.isEmpty()) {
                    for (ArticleVendu article : liste) {
        %>
                        <a href="detailVente?id=<%= article.getNoArticle() %>" class="enchere">
                            <img src="<%= articlesImg.get(String.valueOf(article.getNoArticle())) %>">

                            <div class="enchere_context"">

                                <p class="enchere_nom"><%= article.getNomArticle() %></p>
                                <p class="enchere_prix">Prix : 
                                    <% if (article.getPrixVente() >= article.getMiseAPrix()) { %>
                                        <%= article.getPrixVente() %>
                                    <% } else { %>
                                        <%= article.getMiseAPrix() %>
                                    <% } %>
                                    points
                                </p>
                                <p class="enchere_date">Fin de l'enchère : <%= article.getDateFinEncheres() %></p>

                                <p class="enchere_vendeur">Vendeur : <%= dicUser.get(String.valueOf(article.getNoUtilisateur())) %></p>

                            </div>

                        </a>
        <%
                    }
                } else {
        %>
                    <p>Il n'y a aucune enchères en cours !</p>
        <%
                }
            }
        %>
    </section>

</body>
</html>