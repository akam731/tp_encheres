<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
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
    	<h2>ENI - Ecnhères | Acceuil</h2>
    	<nav>
    	<% 
    	
    	String userId = (String) session.getAttribute("pseudo");
    	
    	if(userId != null){
    		%>
    		<a href="">Enchères</a>
    		<a href="">Vendre un article</a>
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
    <form>
    	<div class="form_flex">
    		<div class="recherche">
    			<div class="img_loupe">
    				<img id="loupe" src="../images/loupe.png">
    			</div>
    			<input id="barre_recherche" type="text" name="rechercher" placeholder="Le nom de l'article contient">
    		</div>
    		<div class="categories">
    			<label>Categorie :</label>
    			<select>
    				<option value="toutes" selected>Toutes</option>
    				<option value="info">Informatique</option>
    				<option>Ameublement</option>
    				<option value="vetements">Vêtements</option>
    				<option value="sport">Sport et Loisirs</option>
    			</select>
    		</div>
    	</div>

    	<input class="rechercher_bouton" type="submit" name="rechercher" value="Rechercher">
    </form>

    <section class="afficher_encheres">
    	
    	<a href="" class="enchere">
    		
    		<img src="fff">

    		<div class="enchere_context">
    			
    			<p class="enchere_nom">PC GAMER pour travailler</p>
    			<p class="enchere_prix">Prix : 210 points</p>
    			<p class="enchere_date">Fin de l'enchère : 10/08/2018</p>
    			<p class="enchere_vendeur">Vendeur : jojo44</p>
    		</div>

    	</a>    	
    	<a href="" class="enchere">
    		
    		<img src="fff">

    		<div class="enchere_context">
    			
    			<p class="enchere_nom">PC GAMER pour travailler</p>
    			<p class="enchere_prix">Prix : 210 points</p>
    			<p class="enchere_date">Fin de l'enchère : 10/08/2018</p>
    			<p class="enchere_vendeur">Vendeur : jojo44</p>
    		</div>

    	</a>	
    	<a href="" class="enchere">
    		
    		<img src="fff">

    		<div class="enchere_context">
    			
    			<p class="enchere_nom">PC GAMER pour travailler</p>
    			<p class="enchere_prix">Prix : 210 points</p>
    			<p class="enchere_date">Fin de l'enchère : 10/08/2018</p>
    			<p class="enchere_vendeur">Vendeur : jojo44</p>
    		</div>

    	</a>

    </section>

</body>
</html>