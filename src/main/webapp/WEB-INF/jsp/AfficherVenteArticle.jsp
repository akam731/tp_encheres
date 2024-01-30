<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Vendre un Article</h2>

<form action="/vendre-article" method="post">
    <!-- Ajoutez ici les champs du formulaire pour saisir les informations sur l'article -->
    <label for="nom">Nom de l'article :</label>
    <input type="text" id="nom" name="nom" required><br>

    <label for="description">Description :</label>
    <textarea id="description" name="description" rows="4" cols="50" required></textarea><br>

    <!-- Ajoutez d'autres champs du formulaire ici -->

    <input type="submit" value="Vendre l'article">
</form>
</body>
</html>