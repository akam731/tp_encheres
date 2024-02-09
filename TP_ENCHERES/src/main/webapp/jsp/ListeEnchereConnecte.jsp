<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des Enchères connecté</title>
<link href="css/listeEnchereConnecteResponsive.css" rel="stylesheet">

</head>
<body>
	<h2>Enchères Actives</h2>
    <ul>
        <c:forEach var="enchere" items="${activeEnchere}">
            <li>${enchere.name}</li>
        </c:forEach>
    </ul>

    <h2>Mes Enchères Participées</h2>
    <ul>
        <c:forEach var="enchere" items="${userParticipatedEnchere}">
            <li>${enchere.name}</li>
        </c:forEach>
    </ul>
</body>
</html>