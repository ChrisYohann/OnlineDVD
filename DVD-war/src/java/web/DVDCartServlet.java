/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.CartLocal;
import ejb.DVD;
import ejb.Utilisateur;
import ejb.UtilisateurSessionLocal;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chris
 */
public class DVDCartServlet extends HttpServlet {

    
    public final static String CART_EDITED = "/Panier.jsp";
    public final static String CART_CHECKED = "/AchatComplete.jsp";

    @EJB
    UtilisateurSessionLocal localuser ;
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DVDCartServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DVDCartServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
        
        CartLocal cart = (CartLocal)request.getSession(true).getAttribute("monpanier");
        List<DVD> dvd_list = cart.getCart();
        Iterator<DVD> iterator = dvd_list.iterator();
        while(iterator.hasNext()){
            DVD dividi = iterator.next();
            if(request.getParameter("delete"+dividi.getId()) != null){
                cart.removeFromCart(dividi);
                request.getSession(true).setAttribute("monpanier", cart);
                request.getServletContext().getRequestDispatcher(CART_EDITED).forward(request,response);
                break ;
            }             
        }
        
        if(request.getParameter("payer") != null){
            UtilisateurSessionLocal user = (UtilisateurSessionLocal)request.getSession(true).getAttribute("user");
            String login = user.getLogin()  ;
            Utilisateur utilise = user.findByLogin(login);
            if(utilise != null)
            cart.checkout(utilise);
            request.getSession(true).setAttribute("monpanier", cart);
            request.getServletContext().getRequestDispatcher(CART_CHECKED).forward(request,response);
        }
        
        
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private UtilisateurSessionLocal lookupUtilisateurSessionLocal() {
        try {
            Context c = new InitialContext();
            return (UtilisateurSessionLocal) c.lookup("java:global/DVD/DVD-ejb/UtilisateurSession!ejb.UtilisateurSessionLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
