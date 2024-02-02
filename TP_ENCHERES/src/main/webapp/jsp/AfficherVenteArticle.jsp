<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/venteArticle.css">
	<title>ENI - Enchères | Mettre en vente</title>
</head>
<body>
	<h2><a href="acceuil">ENI - Enchères</a></h2>
	<h1>Nouvelle vente</h1>
	<form method="post"  action="/TP_ENCHERES/VenteArticle" >
		<label for="nomArticle">Article :</label>
		<input type="text" id="nomArticle" name="nomArticle" required>
		<br>
		<br>
		<div class="box_descr">	
			<label for="description">Description :</label>
			<textarea id="description" name="description" required></textarea>
		</div>
		<br>	
		<label class="label_bottom" for="category">Catégorie :</label>
		<select id="libelle" name="noCategorie" required>
			<option value="1">Mobilier</option>
		</select>
		<br>
		<br>
		<label class="label_bottom" for="image">Photo de l'article :</label>
		<label for="image" class="label_img">Uploader</label>
		<input type="file" id="image_article" name="image_article" >
		<br>
		<label class="label_bottom" for="miseAPrix">Mise à prix :</label>
		<input type="number" min="0" id="miseAPrix" name="miseAPrix" required>
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
			<input class="retrait_input" type="text" id="rue" name="rue" value="" required>
			<br>
			<br>
			<label class="retrait_label" for="codePostal">Code Postal :</label>
			<input class="retrait_input" type="text" id="codePostal" name="codePostal" value="" required>
			<br>
			<br>
			<label class="retrait_label" for="ville">Ville :</label>
			<input class="retrait_input" type="text" id="ville" name="ville" value="" required>
		</fieldset>
		<input type="submit" id="enregistrer" name="enregistrer" value="Enregistrer">
		<input type="submit" id="annuler" name="annuler" value="Annuler">
		<input type="submit" id="annulerVente" name="annulerVente" value="Annuler la vente">
	</form>
</body>
</html>			