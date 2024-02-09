<!DOCTYPE html>
<%@ page import="fr.eni.eni_encheres.bo.Categorie" %>
<%@ page import="java.util.List"%><%@page import="fr.eni.eni_encheres.bo.Enchere"%>
<%@page import="fr.eni.eni_encheres.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="fr.eni.eni_encheres.bo.ArticleVendu" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% ArticleVendu article = (ArticleVendu)session.getAttribute("articleDetail"); 
   Utilisateur vendeur = (Utilisateur)session.getAttribute("articleSeller");
%>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/venteArticle.css">
	<title>ENI - Enchères | Mettre en vente</title>
</head>
<body>
	<h2><a href="acceuil">ENI - Enchères</a></h2>
	<h1>Nouvelle vente</h1>
	<img src="<%= session.getAttribute("articleImg") %>">
	<form method="post"  action="/TP_ENCHERES/modificationEnchere"  enctype="multipart/form-data">
		<label for="nomArticle">Article :</label>
		<input  maxlength="30" type="text" id="nomArticle" name="nomArticle" value="<%= article.getNomArticle()%>" required>
		<br>
		<br>
		<div class="box_descr">	
			<label for="description">Description :</label>
			<textarea  maxlength="300" id="description" name="description" required><%= article.getDescription() %></textarea>
		</div>
		<br>	
		<label class="label_bottom" for="category">Catégorie :</label>
		<%@ include file="/jsp/fragmentListeCategorie.jsp" %>
		<br>
		<br>
		<label class="label_bottom" for="image">Photo de l'article :</label>
		<label for="image" class="label_img">Uploader</label>
		<input type="file" id="image" name="image_article" >
		<br>
		<label class="label_bottom" for="miseAPrix">Mise à prix :</label>
		<input  maxlength="4" type="number" min="0" id="miseAPrix" name="miseAPrix" value="<%= article.getMiseAPrix() %>" required>
		<br>
		<br>
		<label class="label_bottom" for="dateDebutEnchere">Début de l'enchère :</label>
		<input type="date" id="dateDebutEnchere" name="dateDebutEnchere" value="<%= article.getDateDebutEncheres() %>" required>
		<br>
		<br>
		<label class="label_bottom" for="dateFinEnchere">Fin de l'enchère :</label>
		<input type="date" id="dateFinEnchere" name="dateFinEnchere" value="<%= article.getDateFinEncheres() %>" required>
		<br>
		<br>
		<fieldset>
			<legend>Retrait</legend>
			<label class="retrait_label" for="rue">Rue :</label>
			<input  maxlength="30" class="retrait_input" type="text" id="rue" name="rue" value="<%= article.getRue() %>" required>
			<br>
			<br>
			<label class="retrait_label" for="codePostal">Code Postal :</label>
			<input  maxlength="15" class="retrait_input" type="text" id="codePostal" name="codePostal" value="<%= article.getCodePostal() %>" required>
			<br>
			<br>
			<label class="retrait_label" for="ville">Ville :</label>
			<input  maxlength="30" class="retrait_input" type="text" id="ville" name="ville" value="<%= article.getVille() %>" required>
		</fieldset>
		<div class="submits">
			<input type="submit" id="enregistrer" name="enregistrer" value="Enregistrer">
			<a id="annuler" href="acceuil">Annuler</a>
			<%            
			java.util.Date utilDate = new java.util.Date();
            java.sql.Date date = new java.sql.Date(utilDate.getTime());

            if(date.before(article.getDateDebutEncheres())) { %>
			<input type="submit" id="annulerVente" name="annulerVente" value="Annuler la vente">
			<% } %>
		</div>
	</form>
    <div id="erreur"
    <%	String erreur = null;
    	String style = null;
		if(session.getAttribute("errorModif") != null){
			 erreur = (String)session.getAttribute("errorModif");
			 style = "display: block;";
			 session.setAttribute("errorModif",null);
		}
	%> style=" <%= style %> "
    >
        <p><%= erreur %></p>
    </div>
</body>
</html>
<br>
<br>			