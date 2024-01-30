<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ENI - Ecnhères | Inscription</title>
    <link href="../css/inscription.css" rel="stylesheet">
    <link href="css/inscription.css" rel="stylesheet">
</head>
<body>
    <h2><a href="acceuil"> ENI - Enchères </a></h2>
    <form method="post" action="/TP_ENCHERES/inscription">
        <h1>Mon Profil</h1>
        <br>
        <br>
        <div class="formulaire_flex">
          <div class="formulaire_gauche">
            <label for="pseudo">Pseudo :</label>
            <input type="text" id="pseudo" name="pseudo" required><br>

            <label for="prenom">Prénom :</label>
            <input type="text" id="prenom" name="prenom" required><br>

            <label for="telephone">Téléphone :</label>
            <input type="tel" id="telephone" name="telephone" required><br>

            <label for="codePostal">Code Postal :</label>
            <input type="text" id="codePostal" name="codePostal" required><br>

            <label for="motDePasse">Mot de passe :</label>
            <input type="password" id="motDePasse" name="motDePasse" required><br>

          </div>
          <div class="formulaire_droite">
            <label for="nom">Nom :</label>
            <input type="text" id="nom" name="nom" required><br>
            <label for="email">Email :</label>
            <input type="email" id="email" name="email" required><br>

            <label for="rue">Rue :</label>
            <input type="text" id="rue" name="rue" required><br>

            <label for="ville">Ville :</label>
            <input type="text" id="ville" name="ville" required><br>
			

            <label for="motDePasse">Confirmation :</label>
            <input type="password" id="motDePasse" name="motDePasse2" required><br>
          </div>
        </div> 
        <br>
        <br>
        <div class="boutons">
          <input type="submit" name="creer" id="creer" value="Créer">
          <input type="submit" name="annuler" id="annuler" value="Annuler">
        </div>
    </form>
    <div id="erreur"
    <%	String erreur = null;
    	String style = null;
		if(request.getAttribute("errorInscription") != null){
			 erreur = (String)request.getAttribute("errorInscription");
			 style = "display: block;";
			 request.setAttribute("errorInscription",null);
		}
	%> style=" <%= style %> "
    >
        <p><%= erreur %></p>
    </div>
</body>
</html>
