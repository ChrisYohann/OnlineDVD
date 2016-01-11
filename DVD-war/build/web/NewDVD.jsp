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
        <form name="addDVD"  class="col-md-5" method="post" action="DVDAddServlet">
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
        <p><label for="fournisseur">Fournisseur :</label><input type="text" name="fournisseur0name" />
        <label for="quantity">Stock :</label>
        <input type="number" name="fournisseur0count" id="fournisseur0count" min="0" max="20" value="5"></p>        
        <p id="providers"></p>
        <input type="hidden" name="providernumber" value="1">
        <button type ="button" onclick="myFunction(); return false;">  +  </button>
        <br>
        <input class="btn btn-primary" type="submit" name="creer" value="Ajouter" />
        <br>
        </form>
        <script>                            
                function myFunction() {
                var count = parseInt(document.forms["addDVD"].elements["providernumber"].value) ;   
                var new_count = count+1 ;
                document.forms["addDVD"].elements["providernumber"].value = new_count ;
                document.getElementById("providers").innerHTML += "<p><br><label for=\"fournisseur\">Fournisseur "+new_count+" : </label><input type=\"text\" name=\"fournisseur"+count+"name\" /><label for=\"quantity\">Stock :</label>\n\<input type=\"number\" name=\"fournisseur"+count+"count\" id=\"fournisseur"+count+"count\" min=\"0\" max=\"20\" value=\"5\"></p>";
                }
        </script>
        
    </body>
</html>
