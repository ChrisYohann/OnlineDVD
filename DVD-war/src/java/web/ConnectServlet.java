/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.ConnectLocal;
import ejb.Utilisateur;
import ejb.UtilisateurSession;
import ejb.UtilisateurSessionLocal;
import java.io.IOException;
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
public class ConnectServlet extends HttpServlet {
    
    @EJB
    UtilisateurSessionLocal utilisateurSession ;

    @EJB
    private ConnectLocal connect;
    
    public final static String VUE = "/LogSuccessful.jsp" ;
    public final static String VUE_FAILED = "/Connection.jsp";
    
    

    

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
         response.setContentType("text/html;charset=UTF-8");
        /*try (PrintWriter out = response.getWriter()) {
             //TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ConnectServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConnectServlet at " + request.getContextPath() + "</h1>");
            
            out.println(connect.connect("root", "root").getClass().getName());
            out.println("<h1>Salut</h1>");
            
            
            
            
            out.println("</body>");
            out.println("</html>");
        }*/
        
        String login,password ;
        
        login = request.getParameter("login") ;
        password = request.getParameter("mdp") ;
        
        if(login != null && password != null){
            Utilisateur user = connect.connect(login, password) ;
            if(user != null){
                System.out.println("Checking user rights.");
                utilisateurSession.setLogin(login);
                request.getSession(true).setAttribute("user", utilisateurSession);
                request.getServletContext().getRequestDispatcher(VUE).forward(request, response);
                return ;
            } 
                request.getServletContext().getRequestDispatcher(VUE_FAILED).forward(request, response);
                return ;
        }
        request.getServletContext().getRequestDispatcher(VUE_FAILED).forward(request, response);
        return ;
        
        
        
      
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
