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
import java.util.ArrayList;
import java.util.List;
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
    
    public final static String VUE = "/index.jsp";

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
            String dvdTitle,author,director,summary ;
             int instock,numberprovider ;
             
             
             dvdTitle = request.getParameter("dvdTitle");
             author = request.getParameter("dvdauthor");
             director = request.getParameter("director");
             summary = request.getParameter("summary");
             
             try{
                 numberprovider = Integer.parseInt(request.getParameter("providernumber"));
             } catch (NumberFormatException e){
                 numberprovider = 1 ;
             }
                    
             DVD dvd_bean = new DVD();
             List<DVDStock> stocks = new ArrayList<DVDStock>();
             
             dvd_bean.setName(dvdTitle);
             dvd_bean.setAuthor(author);
             dvd_bean.setDirector(director);
             dvd_bean.setDescription(summary);
             
             for(int i = 0 ; i < numberprovider ; i++ ){
                 DVDStock stock = new DVDStock();
                 int en_stock = Integer.parseInt(request.getParameter("fournisseur"+i+"count"));
                 String name_fournisseur = request.getParameter("fournisseur"+i+"name");
                 
                 stock.setNameFournisseur(name_fournisseur);
                 stock.setQuantity(new Long(en_stock));
                 stock.setDVD(dvd_bean);             
                 stocks.add(stock);
             }           
             
            dvdManager.addDVD(dvd_bean, stocks);
            System.out.println("DVD avalaibles :"+dvdManager.getAvailable(dvd_bean));
            request.getServletContext().getRequestDispatcher("VUE").forward(request, response);
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
