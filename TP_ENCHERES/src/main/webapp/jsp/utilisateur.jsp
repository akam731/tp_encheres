<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ENI - Enchères | Profil</title>
    <link href="css/utilisateur.css" rel="stylesheet">
    <link href="css/utilisateurResponsive.css" rel="stylesheet">
</head>
<body>
    <h2><a href="acceuil"> ENI - Enchères </a></h2>
    <form method="post" action="/TP_ENCHERES/connexion">
        <br>
        <label>Pseudo : </label>
        <p><%= request.getAttribute("username") %></p>
        <br>
        <label>Nom : </label>
        <p><%= request.getAttribute("lastName") %></p>
        <br>
        <label>Prénom : </label>
        <p><%= request.getAttribute("firstName") %></p>
        <br>
        <label>Email : </label>
        <p> <%= request.getAttribute("email") %></p>
        <br>
        <label>Teléphone : </label>
        <p><%= request.getAttribute("phoneNumber") %></p>
        <br>
        <label>Rue : </label>
        <p><%= request.getAttribute("street") %></p>
        <br>
        <label>Code Postal : </label>
        <p><%= request.getAttribute("postalCode") %></p>
        <br>
        <label>Ville : </label>
        <p><%= request.getAttribute("city") %></p>
        <br>
        <br>
        <% if(session.getAttribute("pseudo").equals(request.getAttribute("username"))){
        	%>
	        <div class="bouton">
	   		 	<a class="button" href="modificationUtilisateur" id="modifier">Modifier</a>
	   		 </div>
        	<%	
        } 
        	%>
       
    </form>
</body>
</html>
