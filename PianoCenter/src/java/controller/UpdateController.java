/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CourseDAO;
import dto.CourseDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utility.CheckRegisterForm;

/**
 *
 * @author OS
 */
public class UpdateController extends HttpServlet {
    CheckRegisterForm check= new CheckRegisterForm();
    private Date validateDate(String date){
        if(check.checkDate(date)){return Date.valueOf(date);}
        return null;
    }
    private BigDecimal validateTutionFee(String fee){
        if(check.checkDecimalNumber(fee)){return new BigDecimal(fee);}
        return null;
    }
    private Integer validateQuantity(String quantity){
        try {
            return Integer.parseInt(quantity);
        } catch (Exception e) {
            return null;
        }
    }
    private String errorDateMessage(Date date){
        if(date==null){return "Following format: 'Year-Month-Day'";}
        return "";
    }
    private String errorTutionMessage(BigDecimal fee){
        if(fee==null){return "Following format: 'intNumber.number'";}
        return "";
    }
    private String errorQuantityMessage(Integer quantity){
        if(quantity==null){return "Following foramt: intNumber";}
        return "";
    }
    private boolean checkingNullValidation(Date validatedStartDate,Date validatedEndDate,Date validatedCreateDate,BigDecimal validatedTutionFee,Integer validatedQuantity){
        return validatedCreateDate==null||validatedEndDate==null||validatedStartDate==null||validatedTutionFee==null||validatedQuantity==null;
    }
    private String convertStatus(String status){
        if(status!=null){return "Active";}else return "Inactive";
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Integer courseID= Integer.parseInt(request.getParameter("courseID"));
        String name=request.getParameter("courseName");
        String des=request.getParameter("des");
        String tutionFee=request.getParameter("tution");
        String startDate=request.getParameter("startDate");
        String endDate=request.getParameter("endDate");
        String category=request.getParameter("updateCategory");
        String createDate=request.getParameter("createDate");
        String status=convertStatus(request.getParameter("status"));
        String quantity= request.getParameter("quantity");
        String lastUpdateUser= request.getParameter("lastUpdateUser");
                
        Date validatedStartDate=validateDate(startDate);
        Date validatedEndDate=validateDate(endDate);
        Date validatedCreateDate=validateDate(createDate);
        BigDecimal validatedTutionFee=validateTutionFee(tutionFee);
        Integer validatedQuantity=validateQuantity(quantity);
        
        
        
//        out.println(validatedCreateDate);
//        out.println(validatedEndDate);
//        out.println(validatedStartDate);
//        out.println(validatedQuantity);
//        out.println(validatedTutionFee);
//          out.println(!checkingNullValidation(validatedStartDate, validatedEndDate, validatedCreateDate, validatedTutionFee, validatedQuantity));
        
        CourseDAO dao= new CourseDAO();
        boolean result=false;
        if(!checkingNullValidation(validatedStartDate, validatedEndDate, validatedCreateDate, validatedTutionFee, validatedQuantity)){
            CourseDTO course= new CourseDTO(courseID, name, des, validatedTutionFee, validatedStartDate, validatedEndDate, category, validatedCreateDate, lastUpdateUser, status, validatedQuantity);
            result=dao.update(course);
        }else{
            request.setAttribute("dateError", errorDateMessage(validatedEndDate));
            request.setAttribute("dateError", errorDateMessage(validatedStartDate));
            request.setAttribute("dateError", errorDateMessage(validatedCreateDate));
            request.setAttribute("tutionError", errorTutionMessage(validatedTutionFee));
            request.setAttribute("intError", errorQuantityMessage(courseID));
        }
//        out.println(request.getParameter("txtSearch"));
//        out.print(request.getParameter("category"));
        String url=MainController.searchController;
        
        try{
            RequestDispatcher rd= request.getRequestDispatcher(url);
            rd.forward(request, response);
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
