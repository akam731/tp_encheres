<%@page import="fr.eni.eni_encheres.bo.Enchere"%>
<%@page import="fr.eni.eni_encheres.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="fr.eni.eni_encheres.bo.ArticleVendu" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% ArticleVendu article = (ArticleVendu)request.getAttribute("article"); 
   Utilisateur vendeur = (Utilisateur)request.getAttribute("articleSeller");
   Enchere enchere = (Enchere)request.getAttribute("enchere");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>ENI - Enchères | Enchère</title>
	<link rel="stylesheet" type="text/css" href="css/detailVente.css">
</head>
<body>
<h2><a href="acceuil">ENI - Enchères</a></h2>
	<h1>Détail vente</h1>
	<br>
		<% if(!request.getAttribute("articleImg").equals("img_encheres/default.jpg")){ %>
		
			<img src="<%= request.getAttribute("articleImg") %>">
		
		<%} %>
		
		<form method="post"  action="/TP_ENCHERES/detailVente?id=<%= article.getNoArticle() %>">
			<h4><%= article.getNomArticle() %></h4>
			<br>
			<br>
			<br>
			<div class="box_descr">	
				<label>Description :</label>
				<p class="description"><%= article.getDescription() %></p>
			</div>
			<label>Catégorie :</label>
			<p><%= request.getAttribute("articleCategoryName") %></p>	
			<br>
			<label>Meilleur offre :</label>
			<p><%= article.getPrixVente()%> crédits.</p>
			<br>
			<label>Mise à prix :</label>
			<p><%= article.getMiseAPrix() %> crédits</p>
			<br>
			<label>Fin de l'enchère :</label>
			<p><%= article.getDateFinEncheres() %></p>
			<br>
			<div class="box_descr">	
				<label>Retrait :</label>
				<p class="description"><%= article.getRue() %><br><%= article.getCodePostal() %>  <%= article.getVille() %></p>
			</div>
			<label class="label_bottom" for="miseAPrix">Vendeur :</label>
			<p><%= vendeur.getPseudo() %></p>
			<br>
			<label for="enchere">Ma proposition :</label>
			<%
			int enchereUtilisateur = 0;
			if((int)session.getAttribute("noUtilisateur") == (enchere.getNo_Utilisateur())){enchereUtilisateur = enchere.getMontantEnchere();} %>
			<input id="enchere" type="number" name="enchere" maxlength="4" value="<%= enchereUtilisateur %>">
			<input type="submit" id="enregistrer" name="Encherir" value="Encherir">
		</form>
    <div id="erreur"
    <%	String erreur = null;
    	String style = null;
		if(request.getAttribute("erreurDetailEnchere") != null){
			 erreur = (String)request.getAttribute("erreurDetailEnchere");
			 style = "display: block;";
			 request.setAttribute("erreurDetailEnchere",null);
		}
	%> style=" <%= style %> "
    >
        <p><%= erreur %></p>
    </div>
	<br>
	<br>
	<br>	
</body>
</html>