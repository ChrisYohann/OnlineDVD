/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;


import ejb.DVD;
import ejb.DVDManagerLocal;
import ejb.DVDStock;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chris
 */
public class DVDAddServlet extends HttpServlet {

    @EJB
    private DVDManagerLocal dvdManager;

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DVDAddServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DVDAddServlet at " + request.getContextPath() + "</h1>");
            
            String dvdTitle,author,director,summary ;
             int instock ;
        
             dvdTitle = request.getParameter("dvdTitle");
             author = request.getParameter("dvdauthor");
             director = request.getParameter("director");
             summary = request.getParameter("summary");
             
             try{
                 instock = Integer.parseInt(request.getParameter("instock"));
             } catch (NumberFormatException e){
                 instock = 0 ;
             }
             
        
             DVD dvd_bean = new DVD();
             DVDStock stock = new DVDStock();
             
             stock.setStock(new Long(instock));
             stock.setStock(dvd_bean.getId());
        
             dvd_bean.setName(dvdTitle);
             dvd_bean.setAuthor(author);
             dvd_bean.setDirector(director);
             dvd_bean.setDescription(summary);
             dvd_bean.setStock(stock) ;
             
             dvdManager.addDVD(dvd_bean);
             
            
            out.println("</body>");
            out.println("</html>");
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

}
