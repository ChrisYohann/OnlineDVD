
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ejb.*"%>

<%@ include file="entete.jsp" %>
<!DOCTYPE html> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panier</title>
    </head>
    <body>
        
       
       <% 
       
       ejb.CartLocal panier = (ejb.CartLocal) request.getSession(true).getAttribute("monpanier");
            if (panier!= null && panier.getCart().size() > 0){           
               
       %>
               
                <h1 class="Spect">Votre panier:</h1>
        
                <div class="row">
            
                <div class="col-md-offset-1 col-md-5">
                <form method="post" action="DVDCartServlet">
                         <c:forEach items="${monpanier.getCart()}" var="spectvar" >
                        

                <artSpect class="row">
                    
                    <input type="checkbox" name="${spectvar.getId()}" id="idspect" checked> 

                    <label for="nomspectacle">${spectvar.getName()}</label>
                    </artspect>
                    <input class="btn btn-primary" type="submit" name="delete${spectvar.getId()}" value="Retirer" />
                        
                    <br> 
                
                 </c:forEach>
                    <br><br>
                <input class="btn btn-primary" type="submit" name="payer" value="Payer" />
                </form>
                <% } 
            else {%>
                
                <h1 class="Vide">Votre panier est vide</h1>
                
                <% } %>
                    </div>
    </body>
</html>
