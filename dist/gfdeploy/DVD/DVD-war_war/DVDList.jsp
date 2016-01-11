<%-- 
    Document   : Spectacle
    Created on : 21 avr. 2015, 17:05:45
    Author     : igierm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="ejb.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="entete.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="Spectacle.js"></script>
        <title>Spectacle</title>
    </head>
    <body>
        <h1 class="Spect">DVDs</h1>
     
<p>  <span id='tab'/> Recherche DVD</p>
        <div class="row">
            <article class="col-md-offset-1 col-md-10">
                <c:forEach items="${liste_dvd}" var="spectvar" >

                <artSpect class="row">  
                    <img class="col-md-5" height=500px src="image/Scoop.jpg"/>
                    <p class="col-md-5">Titre : ${spectvar.getName()}<br>
                        Auteur : ${spectvar.getAuthor()}<br>
                        Réalisateur : ${spectvar.getDirector()}<br>
                        <br><br>${spectvar.getDescription()}
                        
                    <form  class="col-md-5" method="post" action="DVDAddCartServlet">
                        
                            <br><br>
                            
                            <input class="btn btn-primary" type="submit" name="ajout de ${spectvar.getId()}" value="Ajouter au panier" /></form> 
                            <c:if test="${not empty user}">
                                <c:if test="${user.isAdmin()}">
                                    <form  class="col-md-5" method="post" action="SpectacleManagementCo">
                                        <input class="btn btn-primary" type="submit" name="modifier ${spectvar.getId()}" value="Gérer le Stock" />
                                    </form>
                                   <form  class="col-md-5" method="post" action="GestionBookings">
                                        <input class="btn btn-primary" type="submit" name="gestion ${spectvar.getId()}" value="Voir les commandes" />
                                    </form>                                   
                                </c:if>    
                            </c:if>
                            <br>
                          
                    </artSpect>
                </c:forEach>
            </article>
        </div>
    </body>
</html>
