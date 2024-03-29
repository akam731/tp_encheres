<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ENI - Enchères | Connexion</title>
    <link href="css/connexion.css" rel="stylesheet">
   <!-- <link href="css/connexionResponsive.css" rel="stylesheet">-->
</head>
<body>
    <h2><a href="acceuil"> ENI - Enchères </a></h2>
    <form method="post" action="/TP_ENCHERES/connexion">
        <h1>Connexion</h1>
        <br>
        <br>
        <label for="pseudo">Pseudo :</label>
        <input type="text" id="pseudo" name="pseudo" required><br>
        <label for="motDePasse">Mot de passe :</label>
        <input type="password" id="motDePasse" name="motDePasse" required><br>
        <br>
        <br>
        <div class="conec_flex">
          <input type="submit" name="connexion" id="connexion" value="Connexion">
          <div class="souvenir_oublié">
             <div class="souvenir">
                <input type="checkbox" id="souvenir" name="souvenir">
                <label for="souvenir" id="label_souvenir">Se souvenir de moi</label>
             </div>
             <a href="">Mot de passe oublié</a>
          </div>
        </div>
        <br>
        <br>
        <br>
    <a class="button" href="inscription" id="inscription">Inscription</a>
    </form>
    <div id="erreur"
    <%	String erreur = null;
    	String style = null;
		if(request.getAttribute("errorConnexion") != null){
			 erreur = (String)request.getAttribute("errorConnexion");
			 style = "display: block;";
			 request.setAttribute("errorConnexion",null);
		}
	%> style=" <%= style %> "
    >
        <p><%= erreur %></p>
    </div>
</body>
</html>
