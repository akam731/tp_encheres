<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vente d'un article</title>
</head>
<body>
	<h2>Vendre un Article</h2>
<form action="/VenteArticle" method="post">  
    <label for="nom">Nom de l'article :</label>
    <input type="text" id="nomArticle" name="nomArticle" required>
    <br>
    <label for="description">Description :</label>
    <textarea id="description" name="description" rows="4" cols="50" required></textarea>
    <br>
     <label for="prixInitial">Prix initial:</label>
    <input type="number" id="miseAPrix" name="miseAPrix" required>
    <br>
	<label for="noCategorie">Catégorie:</label>
    <select id="noCategorie" name="noCategorie" required>
        <option value="1">Catégorie 1</option>
        <option value="2">Catégorie 2</option>
     </select>
     <label for="PhotoArticle">Photo de l'article</label>
     <br>
     <a href="">Upload photo</a>
     <br>
     <label for="dateDebutEncheres">Date de début d'enchères:</label>
    <input type="datetime-local" id="dateDebutEncheres" name="dateDebutEncheres" value="<fmt:formatDate value='${now}' pattern='yyyy-MM-dd HH:mm'/>" required>
    <br>
    <label for="dateFinEncheres">Date de fin d'enchères:</label>
    <input type="datetime-local" id="dateFinEncheres" name="dateFinEncheres" value="<fmt:formatDate value='${now}' pattern='yyyy-MM-dd HH:mm' />" required>
    <br>
     <label for="rue">Rue:</label>
    <input type="text" id="rue" name="rue" required><br>

    <label for="codePostal">Code postal:</label>
    <input type="text" id="codePostal" name="codePostal" required><br>

    <label for="ville">Ville:</label>
    <input type="text" id="ville" name="ville" required><br>
     
        
    <input type="submit" value="Enregistrer">
</form>
</body>
</html>