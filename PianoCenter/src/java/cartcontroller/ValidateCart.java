/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartcontroller;

import controller.MainController;
import dao.CartDAO;
import dao.CourseDAO;
import dto.CartDTO;
import dto.CourseDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author OS
 */

public class ValidateCart extends HttpServlet {
    private boolean validateQuantity(int num1,int num2){
        return num1-num2>0;
    }
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
    private CourseDTO getCourse(Integer courseID){
        CourseDAO dAO= new CourseDAO();
        return dAO.getCourse(courseID);
    }
    private ArrayList<CourseDTO> getCourseInCart(ArrayList<CartDTO> carts){
        ArrayList<CourseDTO> courses= new ArrayList<>();
        
        for(CartDTO cart: carts){
            if(getCourse(cart.getCourseID())!=null){courses.add(getCourse(cart.getCourseID()));}
        }
        return courses;
    }
    private void updateCourseDAO(ArrayList<CartDTO> carts,ArrayList<CourseDTO> courses,HttpServletRequest request){
        CourseDAO courseDAO= new CourseDAO();
        CartDAO cartDAO= new CartDAO();
        for(CartDTO cart: carts){
            for(CourseDTO course:courses){
                if(cart.getCourseID()==course.getCourseID()){
                    int trueQuantity=course.getQuantity()-cart.getQuantity();
                    if(trueQuantity>=0){
                        course.setQuantity(trueQuantity);
                        courseDAO.update(course);
                        cartDAO.delete(cart);
                    }else{request.setAttribute(String.valueOf(course.getCourseID()), "Not enough quantity");}
                }
            }
        }
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
//        CourseDAO dao = new CourseDAO();
        String cartIDs[]= request.getParameterValues("selectedCarts");
         if (cartIDs == null || cartIDs.length == 0) {
            request.setAttribute("error", "No courses selected.");
            request.getRequestDispatcher(MainController.viewCart).forward(request, response);
            return;
        }

        ArrayList<Integer> cartIDList= convertStringToInt(cartIDs);
        ArrayList<CartDTO> carts= getCarts(cartIDList);
        ArrayList<CourseDTO> courses= getCourseInCart(carts);
//        HttpSession session=request.getSession();
        updateCourseDAO(carts, courses, request);
        
        RequestDispatcher rd=request.getRequestDispatcher(MainController.viewCart);
        rd.forward(request, response);
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
