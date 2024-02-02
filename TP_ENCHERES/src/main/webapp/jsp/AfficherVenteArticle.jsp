<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="retrait" class="fr.eni.eni_encheres.bo.Retrait" scope="request"/>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="styles.css">
	<style type="text/css">
		@import url('https://fonts.googleapis.com/css2?family=Varela+Round&display=swap');
		*{
			font-family: "Varela Round";
		}
		body {
			background-color: #f4f4f4;
			margin: 0;
			padding: 0;
		}
		h1{
			display: block;
			text-align: center;
			font-weight: normal;
		}
		h2{
			display: inline-block;
			font-weight: normal;
			margin: 30px 0 0 50px;
			text-decoration: none;
		}
		h2 a{
			text-decoration: none;
			color: black;
		}

		form {
			background-color: #fff;
			padding: 20px 40px 20px 40px;
			border-radius: 8px;
			box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
			width: 440px;
			height: 700px;
			margin: 20px auto 0 auto;
		}

		label {
			display: inline-block;
			margin-bottom: 5px;
			width: 150px;
		}

		.label_bottom
		{
			width: 200px;
		}

		.box_descr
		{
			display: flex;
			width: 100%;
		}

		input[type="text"],
		input[type="number"],
		input[type="date"],
		select {
			padding: 5px;
			display: inline-block;
			width: 250px;
			padding: 8px;
			border: 1px solid black;
		}

		select{
			width: 220px;
			height: 30px;
		}

		textarea {
			margin: 0 0 0 5px;
			min-width: 245px;
			max-width: 245px;
			padding: 10px;
			min-height: 100px;
			max-height: 200px;
			border: 1px solid black;
		}

		input[type="file"] {
			visibility: hidden;
		}

		input[type="number"] {
			width: 100px;
			height: 10px;
		}

		input[type="date"] {
			width: 150px;
		}

		.label_img
		{
			cursor: pointer;
			display: inline-block;
			height: 20px;
			border: 1px solid black;
			width: 215px;
			text-align: center;
			text-transform: uppercase;
		}

		.retrait_label{
			width: 110px;
		}

		.retrait_input{
			width: 90px;
		}

		#enregistrer{
			cursor: pointer;
			margin: 20px 0 0 0;
			height: 50px;
			width: 100px;
			border: 1px solid #0AACD7;
			background-color: #0AACD7;
			color: #fff;
		}

		#enregistrer:hover{
			background-color: #fff;
			color: #0AACD7;
		}

		#annuler{
			cursor: pointer;
			margin: 20px 0 0 30px;
			height: 50px;
			width: 100px;
			border: 1px solid #D70A0A;
			background-color: #D70A0A;
			color: #fff;
		}

		#annuler:hover{
			background-color: #fff;
			color: #D70A0A;
		}

		#annulerVente{
			cursor: pointer;
			margin: 20px 0 0 30px;
			height: 50px;
			width: 150px;
			border: 1px solid #D70A0A;
			background-color: #D70A0A;
			color: #fff;
		}

		#annulerVente:hover{
			background-color: #fff;
			color: #D70A0A;
		}

	</style>
</head>
<body>
	<h2><a href="">ENI - Enchères</a></h2>
	<h1>Nouvelle vente</h1>
	<form action="/VenteArticle" method="post">
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
		<select id="libelle" name="libelle" required>
			<option value="mobilier">Mobilier</option>
		</select>
		<br>
		<br>
		<label class="label_bottom" for="image">Photo de l'article :</label>
		<label for="image" class="label_img">Uploader</label>
		<input type="file" id="image_article" name="image_article" required>
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
			<input class="retrait_input" type="text" id="rue" name="rue" value="${retrait.rue}" required>
			<br>
			<br>
			<label class="retrait_label" for="codePostal">Code Postal :</label>
			<input class="retrait_input" type="text" id="codePostal" name="codePostal" value="${retrait.codePostal}" required>
			<br>
			<br>
			<label class="retrait_label" for="ville">Ville :</label>
			<input class="retrait_input" type="text" id="ville" name="ville" value="${retrait.ville}" required>
		</fieldset>
		<input type="submit" id="enregistrer" name="enregistrer" value="Enregistrer">
		<input type="submit" id="annuler" name="annuler" value="Annuler">
		<input type="submit" id="annulerVente" name="annulerVente" value="Annuler la vente">
	</form>
</body>
</html>			