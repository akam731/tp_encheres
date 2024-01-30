<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulaire d'inscription</title>
    <style>
      @import url('https://fonts.googleapis.com/css2?family=Varela+Round&display=swap');
      *{
        font-family: "Varela Round";
      }
        body {
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        h2{
          font-weight: normal;
          margin: 50px 0 0 50px;
        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 1000px;
            height: 500px;
            margin: 150px auto 0 auto;
        }

        h1{
          font-weight: normal;
          text-align: center;
        }

        .formulaire_flex{
            display: flex;
            justify-content: space-around;
        }

        label {
            display: inline-block;
            margin-bottom: 8px;
            width: 150px;
        }

        input {
            width: 200px;
            padding: 8px;
            margin-bottom: 16px;
        }

        input[type=submit] {
            display: inline-block;
            color: #fff;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 200px;
        }        

        #annuler:hover {
          color: red;
          background: transparent;
        }

        #creer:hover{
          color: green;
          background: transparent;
        }

        #creer{
          margin: 0 50px 0 0;
            background-color: green;
            border: 1px solid green;
        }

        #annuler{
          margin: 0 0 0 50px;
          background-color: red;
            border: 1px solid red;
        }

        .boutons{
          display: flex;
          justify-content: center;
        }
    </style>
</head>
<body>
    <h2>ENI - Enchères</h2>
    <form>
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
            <input type="password" id="motDePasse" name="motDePasse" required><br>
          </div>
        </div> 
        <br>
        <br>
        <div class="boutons">
          <input type="submit" name="creer" id="creer" value="Créer">
          <input type="submit" name="annuler" id="annuler" value="Annuler">
        </div>
    </form>
</body>
</html>
