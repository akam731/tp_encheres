<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Formulaire de Connexion</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      display: flex;
      align-items: center;
      justify-content: center;
      height: 100vh;
    }
    .login-container {
      background-color: #fff;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      padding: 20px;
      width: 300px;
      text-align: center;
    }
    .login-container h2 {
      color: #333;
    }
    .login-container label {
      display: block;
      text-align: left;
      margin-bottom: 8px;
      color: #555;
    }
    .login-container input {
      width: 100%;
      padding: 10px;
      margin-bottom: 15px;
      box-sizing: border-box;
      border: 1px solid #ccc;
      border-radius: 4px;
      outline: none;
    }
    .login-container input[type="checkbox"] {
      display: inline-block;
      margin-right: 5px;
    }
    .login-container a {
      color: #3498db;
      text-decoration: none;
      display: block;
      margin-top: 10px;
    }
    .login-container button {
      background-color: #3498db;
      color: #fff;
      padding: 10px 15px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      font-size: 16px;
    }
    .login-container button:hover {
      background-color: #2980b9;
    }
  </style>
</head>
<body>

<div class="login-container">
  <h2>Connexion</h2>
  <form action="process-login.php" method="post"> <!-- Remplacez process-login.php par le script de gestion de la connexion côté serveur -->

    <label for="identifiant">Identifiant:</label>
    <input type="text" id="identifiant" name="identifiant" required>

    <label for="mot-de-passe">Mot de passe:</label>
    <input type="password" id="mot-de-passe" name="mot-de-passe" required>

    <input type="checkbox" id="se-souvenir-de-moi" name="se-souvenir-de-moi">
    <label for="se-souvenir-de-moi">Se souvenir de moi</label>

    <br>

    <a href="mot-de-passe-oublie.html">Mot de passe oublié</a>

    <br>

    <button type="submit">Connexion</button>

  </form>
</div>

</body>
</html>
