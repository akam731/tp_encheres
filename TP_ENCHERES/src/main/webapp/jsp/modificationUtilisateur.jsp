<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ENI - Ecnhères | Inscription</title>
    <link href="css/modifUtilisateur.css" rel="stylesheet">
   <!-- <link href="css/modifUtilisateurResponsive.css" rel="stylesheet">-->
</head>
<body>
    <h2><a href="acceuil"> ENI - Enchères </a></h2>
    <form method="post" action="/TP_ENCHERES/modificationUtilisateur">
        <h1>Mon Profil</h1>
        <br>
        <br>
        <div class="formulaire_flex">
          <div class="formulaire_gauche">
            <label for="pseudo">Pseudo : *</label>
            <input maxlength="30" type="text" id="pseudo" name="pseudo" value="<%= session.getAttribute("pseudo") %>" required><br>

            <label for="prenom">Prénom : *</label>
            <input maxlength="30" type="text" id="prenom" name="prenom" value="<%= session.getAttribute("prenom") %>" required><br>

            <label for="telephone">Téléphone : *</label>
            <input maxlength="15" type="tel" id="telephone" name="telephone" value="<%= session.getAttribute("tel") %>" required><br>

            <label for="codePostal">Code Postal : *</label>
            <input maxlength="10" type="text" id="codePostal" name="codePostal" value="<%= session.getAttribute("codePostal") %>" required><br>

            <label for="motDePasse">Mot de passe actuel : *</label>
            <input maxlength="30" type="password" id="motDePasse" name="motDePasse" required><br>

            <label for="Nouveau">Nouveau mot de passe :</label>
            <input maxlength="30" type="password" id="Nouveau" name="motDePasse2" ><br>

            <p>Credits : <%= session.getAttribute("credits") %></p>

          </div>
          <div class="formulaire_droite">
            <label for="nom">Nom : *</label>
            <input maxlength="30" type="text" id="nom" name="nom" value="<%= session.getAttribute("nom") %>" required><br>
            <label for="email">Email : *</label>
            <input maxlength="50" type="email" id="email" name="email" value="<%= session.getAttribute("mail") %>" required><br>

            <label for="rue">Rue : *</label>
            <input maxlength="50" type="text" id="rue" name="rue" value="<%= session.getAttribute("rue") %>" required><br>

            <label for="ville">Ville : *</label>
            <input maxlength="30" type="text" id="ville" name="ville" value="<%= session.getAttribute("ville") %>" required><br>

            <label style="visibility: hidden;"></label>
            <input style="visibility: hidden;"><br>

            <label for="Confirmation">Confirmation :</label>
            <input maxlength="30" type="password" id="Confirmation" name="motDePasse3"><br>
          </div>
        </div> 
        <br>
        <br>
        <div class="boutons">
          <input type="submit" name="enregistrer" id="enregistrer" value="Enregistrer">
          <input type="submit" name="suppimer" id="suppimer" value="Suppimer mon compte" onclick="confirmerSuppression()">
        </div>
    </form>
    
    <div id="erreur"
    <%	String erreur = null;
    	String style = null;
		if(request.getAttribute("erreurModif") != null){
			 erreur = (String)request.getAttribute("erreurModif");
			 style = "display: block;";
			 request.setAttribute("erreurModif",null);
		}
	%> style=" <%= style %> "
    >
        <p><%= erreur %></p>
    </div>
</body>
</html>
