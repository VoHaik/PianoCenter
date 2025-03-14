/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartcontroller;

import controller.MainController;
import dao.CartDAO;
import dto.CartDTO;
import dto.CourseDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 *
 * @author dinh
 */
public class AddToCartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private List<CartDTO> getUserCart(CartDAO dao,String userID){
        ArrayList<CartDTO> carts=new ArrayList<>();
        if(dao.read(userID)!=null){
            carts= (ArrayList<CartDTO>)dao.read(userID);
        }
        return carts;
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String url=MainController.searchController;
            CartDTO cart= new CartDTO();
            HttpSession session = request.getSession();
            Integer courseID = Integer.parseInt(request.getParameter("courseID"));
            String userID= (String)session.getAttribute("username");
            if(userID==null){userID="Guest";}
            cart.setUserID(userID);
            cart.setCourseID(courseID);
            cart.setQuantity(1);
            boolean result=false;
            try {
                CartDAO dao= new CartDAO();
                result=dao.createCart(cart);
                if(result){
                    session.setAttribute("cartUser", getUserCart(dao, userID));
                    RequestDispatcher rd= request.getRequestDispatcher(url);
                    rd.forward(request, response);
                }else{out.print("Your cart was no created correctly");}
            } catch (Exception e) {
                e.printStackTrace();
            }
            
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
