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

/**
 *
 * @author OS
 */
public class MainController extends HttpServlet {
    static final String loginPage="LoginPage.jsp";
    static final String registerPage="RegisterPage.jsp";
    static final String checkingController="CheckRegisterForm";
    static final String loginController="LoginController";
    static final String homePage="HomePage.jsp";
    static final String searchController="SearchController";
    static final String logoutController="LogoutController";
    static final String authorizationController="AuthorizationController";
    static final String homePageAdmin="Admin.jsp";
    static final String updateController="UpdateController";
    static final String createCoursePage="createCoursePage.jsp";
    static final String checkingCourseController="CheckingCourseController";
    static final String addToCartController="AddToCartController";

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
        try{
            if(url.isEmpty()){
                if(action.matches("Login")){url=loginController;}
                else if(action.equals("Register")){url=checkingController;}
                else if(action.equals("Search")){url=searchController;}
                else if(action.equals("Logout")){url=logoutController;}
                else if(action.equals("Update")){url=updateController;}
                else if(action.equals("createCourse")) {url=checkingCourseController;}
                else if(action.equals("Add to cart")) {url=addToCartController;}
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
