<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profil Utilisateur</title>
<link href="css/profilUtilisateurResponsive.css" rel="stylesheet">
</head>
<body>
	<h1>Profil de <%= request.getAttribute("") %></h1>
    <p>Nom : <%= request.getAttribute("") %></p>
    <p>Prénom : <%= request.getAttribute("") %></p>
    <p>Email : <%= request.getAttribute("") %></p>
    <p>Numéro de téléphone : <%= request.getAttribute("") %></p>
    <p>Adresse : <%= request.getAttribute("") %>, <%= request.getAttribute("postalCode") %> <%= request.getAttribute("city") %></p>
</body>
</html>