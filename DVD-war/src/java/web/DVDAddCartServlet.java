/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.CartLocal;
import ejb.DVD;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
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
public class DVDAddCartServlet extends HttpServlet {
    
    public final static String VUE = "/AchatComplete.jsp";
    
    @EJB
    CartLocal cart ;
    
    

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
       /* response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code.
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DVDAddCartServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DVDAddCartServlet at " + request.getContextPath() + "</h1>");
            
            gerer(request);
            request.setAttribute("monpanier", cart);
            for(DVD dvdincart : cart.getCart()){
                out.println("DVD in cart : "+dvdincart+ "<br>");
            }
            
            
            
            
            out.println("</body>");
            out.println("</html>");
        }*/
       
            gerer(request);
            request.getSession(true).setAttribute("monpanier", cart);
            request.getServletContext().getRequestDispatcher(VUE).forward(request, response);
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
    
    public void gerer(HttpServletRequest request){
        
        int oui = 1;
        //CartLocal cartbean = (CartLocal)request.getSession(true).getAttribute("monpanier");
        List<DVD> spectacle = (List<DVD>)request.getSession(true).getAttribute("liste_dvd");
        Iterator<DVD> it_dvd = spectacle.iterator();
                
           //PreReservation preticket = new PreReservation();
           
           while (it_dvd.hasNext()) {
           
               DVD dvd = it_dvd.next();
               Long  dvd_id = dvd.getId() ;
               String dvd_name = dvd.getName();
                     
               if(request.getParameter("ajout de " + dvd_id)!=null){
                    cart.addDVDToCart(dvd);          
                }
           
           }
           
    }

    private CartLocal lookupCartLocal() {
        try {
            Context c = new InitialContext();
            return (CartLocal) c.lookup("java:global/DVD/DVD-ejb/Cart!ejb.CartLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
