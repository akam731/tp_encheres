<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="fr.eni.eni_encheres.bo.Categorie" %>
<%@ page import="java.util.List"%>

<select name="categorie">
	<c:forEach items="${categories}" var="categorie">
   		<option value="${categorie.noCategorie}">${categorie.libelle}</option>
	</c:forEach>
</select>
