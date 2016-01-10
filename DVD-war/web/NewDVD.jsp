<%-- 
    Document   : NewDVD
    Created on : 10 janv. 2016, 13:33:06
    Author     : chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="entete.jsp" %>

<!DOCTYPE html>
<html>
    <body>
        <form  class="col-md-5" method="post" action="DVDAddServlet">
        <label for="nomdvd">Titre du DVD :</label>
        <p><input type="text" name="dvdTitle" /></p>
        <label for="nomdvd">Auteur :</label>
        <p><input type="text" name="dvdauthor" /></p>
        <label for="nomdvd">Réalisateur :</label>
        <p><input type="text" name="director" /></p>
        <label for="description">Résumé (max 200 caractères) :</label>
        <p><TEXTAREA name="summary" rows=10 COLS=40 maxlength="200"></TEXTAREA></p>
        <label for="affiche">Jaquette (sans l'extension .jpg):</label>
        <p><input type="text" name="affiche" /></p>
        <br>
        <label for="quantity">Quantité en Stock :</label>
        <input type="number" name="instock" id="instock" min="0" max="20" value="5">
        <br>
        <input class="btn btn-primary" type="submit" name="creer" value="Ajouter" />
        <br>
        </form>
    </body>
</html>
