/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDAO;
import dto.UserDTO;
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
public class LoginController extends HttpServlet {
    private UserDTO getUser(String username){
        ArrayList<UserDTO> users= (ArrayList<UserDTO>) new UserDAO().read(username);
        if(users!=null){
            for(UserDTO user: users){
                if(user.getUserID().matches(username)){return user;}
            }
        }    
        return null;
    }
    private String checkLogin(String username,String password){
        UserDTO user=getUser(username);
        if(user==null){return null;}
        if(user.getUserID().matches(username)&&user.getPassword().matches(password)){return user.getRole();}
        return "Guest";
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String username=request.getParameter("txtUsername");
        String password=request.getParameter("txtPassword");
        String url;
        HttpSession session= request.getSession();
        String validateLogin= checkLogin(username, password);
        session.setAttribute("role", validateLogin);
        
        try{
            if(!validateLogin.equalsIgnoreCase("Guest")){
               url=MainController.authorizationController;
               session.setAttribute("username", username);
               session.setAttribute("invalidLogin", true);
            }
            else{
                url=MainController.loginPage;
                session.setAttribute("invalidLogin", false);
            }
            RequestDispatcher rd= request.getRequestDispatcher(url);
            rd.forward(request, response);
        }catch(Exception e){
            e.printStackTrace();
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
