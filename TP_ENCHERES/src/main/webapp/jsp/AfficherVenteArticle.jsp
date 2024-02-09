<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="fr.eni.eni_encheres.bo.Categorie" %>
<%@ page import="java.util.List"%>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/venteArticle.css">
<!--	<link rel="stylesheet" type="text/css" href="css/venteArticleResponsive.css"> -->
	<title>ENI - Enchères | Mettre en vente</title>
</head>
<body>
	<h2><a href="acceuil">ENI - Enchères</a></h2>
	<h1>Nouvelle vente</h1>
	<form method="post"  action="/TP_ENCHERES/VenteArticle"  enctype="multipart/form-data">
		<label for="nomArticle">Article :</label>
		<input  maxlength="30" type="text" id="nomArticle" name="nomArticle" required>
		<br>
		<br>
		<div class="box_descr">	
			<label for="description">Description :</label>
			<textarea  maxlength="300" id="description" name="description" required></textarea>
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
		<input  maxlength="4" type="number" min="0" id="miseAPrix" name="miseAPrix" required>
		<br>
		<br>
		<label class="label_bottom" for="dateDebutEnchere">Début de l'enchère :</label>
		<input type="date" id="dateDebutEnchere" name="dateDebutEnchere" required>
		<br>
		<br>
		<label class="label_bottom" for="dateFinEnchere">Fin de l'enchère :</label>
		<input type="date" id="dateFinEnchere" name="dateFinEnchere" required>
		<br>
		<br>
		<fieldset>
			<legend>Retrait</legend>
			<label class="retrait_label" for="rue">Rue :</label>
			<input  maxlength="30" class="retrait_input" type="text" id="rue" name="rue" value="${rue}" required>
			<br>
			<br>
			<label class="retrait_label" for="codePostal">Code Postal :</label>
			<input  maxlength="15" class="retrait_input" type="text" id="codePostal" name="codePostal" value="${codePostal }" required>
			<br>
			<br>
			<label class="retrait_label" for="ville">Ville :</label>
			<input  maxlength="30" class="retrait_input" type="text" id="ville" name="ville" value="${ville }" required>
		</fieldset>
		<div class="submits">
			<input type="submit" id="enregistrer" name="enregistrer" value="Enregistrer">
			<a id="annuler" href="acceuil">Annuler</a>
		</div>
		<!--<input type="submit" id="annulerVente" name="annulerVente" value="Annuler la vente">-->
	</form>
    <div id="erreur"
    <%	String erreur = null;
    	String style = null;
		if(request.getAttribute("errorArticle") != null){
			 erreur = (String)request.getAttribute("errorArticle");
			 style = "display: block;";
			 request.setAttribute("errorArticle",null);
		}
	%> style=" <%= style %> "
    >
        <p><%= erreur %></p>
    </div>
</body>
</html>
<br>
<br>			