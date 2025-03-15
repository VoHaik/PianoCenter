/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
public class MainController extends HttpServlet {
    public static final String loginPage="LoginPage.jsp";
    public static final String registerPage="RegisterPage.jsp";
    public static final String checkingController="CheckRegisterForm";
    public static final String loginController="LoginController";
    public static final String homePage="HomePage.jsp";
    public static final String searchController="SearchController";
    public static final String logoutController="LogoutController";
    public static final String authorizationController="AuthorizationController";
    public static final String homePageAdmin="Admin.jsp";
    public static final String updateController="UpdateController";
    public static final String createCoursePage="createCoursePage.jsp";
    public static final String checkingCourseController="CheckingCourseController";
    public static final String addToCartController="AddToCartController";
    public static final String validateCartController="ValidateCart";
    public static final String viewCart="ViewCart.jsp";
    public static final String updateCartController="UpdateCart";

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
        PrintWriter out = response.getWriter();
        String action=request.getParameter("btAction");
        String url="";
        HttpSession session= request.getSession();
        try{
            if(url.isEmpty()){
                if(action.matches("Login")){url=loginController;}
                else if(action.equals("Register")){url=checkingController;}
                else if(action.equals("Search")){url=searchController;}
                else if(action.equals("Logout")){url=logoutController;}
                else if(action.equals("Update")){url=updateController;}
                else if(action.equals("createCourse")) {url=checkingCourseController;}
                else if(action.equals("Add to cart")) {
                    if(session.getAttribute("username")==null){
                        url=registerPage;
                    }else{url=addToCartController;}
                }
                else if(action.equals("Booking")) {url=validateCartController;}
                else if(action.equals("updateCart")) {url=updateCartController;}
                RequestDispatcher rd= request.getRequestDispatcher(url);
                rd.forward(request, response);
            }else{out.println("Error With url");}
        }catch(Exception e){e.printStackTrace();}
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
