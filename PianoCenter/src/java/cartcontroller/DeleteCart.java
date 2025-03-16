/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartcontroller;

import controller.MainController;
import dao.CartDAO;
import dto.CartDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dinh
 */
public class DeleteCart extends HttpServlet {

    private  ArrayList<Integer> convertStringToInt(String[] cartIDs){
        ArrayList<Integer> cartIDList= new ArrayList<>();
        for(String id: cartIDs){
            cartIDList.add(Integer.parseInt(id));
        }
        return cartIDList;
    }
    private ArrayList<CartDTO> getCarts(ArrayList<Integer> cartIDList){
        CartDAO dAO= new CartDAO();
        ArrayList<CartDTO> carts= new ArrayList<>();
        for(Integer id: cartIDList){
            CartDTO cart = dAO.getCart(String.valueOf(id));
            if(cart!=null){carts.add(cart);}
        }
        return carts;
    }
    private boolean deleteCarts(ArrayList<CartDTO> carts){
        boolean check=false;
        CartDAO dao= new CartDAO();
        if(carts!=null){
            for(CartDTO cart:carts){
                dao.delete(cart);
                check=true;
            }
        }
        return check;
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           String url = MainController.viewCart;
           String cartIDs []= request.getParameterValues("selectedCarts");
           if (cartIDs == null || cartIDs.length == 0) {
            request.setAttribute("error", "No courses selected.");
            request.getRequestDispatcher(MainController.viewCart).forward(request, response);
            return;
            }
           ArrayList<Integer> cartIDList =convertStringToInt(cartIDs);
           ArrayList<CartDTO> carts= getCarts(cartIDList);
           
           if(deleteCarts(carts)){
               RequestDispatcher rd= request.getRequestDispatcher(url);
               rd.forward(request, response);
           }else{out.print("Cart was not deleted correctly");}
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
