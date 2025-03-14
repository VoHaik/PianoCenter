/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CourseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utility.CheckCourseForm;

/**
 *
 * @author dinh
 */
public class CheckingCourseController extends HttpServlet {

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
        String url = "createCoursePage.jsp";
        
        try {
            String name = request.getParameter("txtCourseName");
            String image = request.getParameter("txtCourseImage");
            String description = request.getParameter("txtCourseDescription");
            String tuitionFee = request.getParameter("txtTuitionFee");
            String startDate = request.getParameter("txtStartDate");
            String endDate = request.getParameter("txtEndDate");
            String category = request.getParameter("txtCategory");
            String status = request.getParameter("txtStatus");
            int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
            
            CheckCourseForm checking = new CheckCourseForm();
            CourseDAO dao = new CourseDAO();

            boolean error1 = checking.checkName(name);
            boolean error3 = checking.checkDescription(description);
            boolean error4 = checking.checkTuitionFee(tuitionFee);
            boolean error5 = checking.checkDates(startDate, endDate);
            boolean error6 = checking.checkCategory(category);
            boolean error7 = checking.checkStatus(status);
            boolean error8 = checking.checkQuantity(quantity);

            request.setAttribute("invalidName", error1);
            request.setAttribute("invalidDescription", error3);
            request.setAttribute("invalidTuitionFee", error4);
            request.setAttribute("invalidDates", error5);
            request.setAttribute("invalidCategory", error6);
            request.setAttribute("invalidStatus", error7);
            request.setAttribute("invalidQuantity", error8);

            if (error1 && error3 && error4 && error5 && error6 && error7 && error8) {
                url = "CreateCourseController";
            }

        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
