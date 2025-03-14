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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author OS
 */
public class SearchController extends HttpServlet {

    private List<CourseDTO> searchCourseByNameAndCategory(String name,String category,int OFFSET,int PageSize){
        
        CourseDAO dao= new CourseDAO();
        ArrayList<CourseDTO> courses= (ArrayList<CourseDTO>)dao.read(name,category,OFFSET,PageSize);
        if(category.equalsIgnoreCase("all")){return courses;}
        return courses;
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        
        String searchValue=request.getParameter("txtSearch");
        request.setAttribute("txtSearch", searchValue);
        
        String category=request.getParameter("category");
        request.setAttribute("category", category);
        
        String url=MainController.authorizationController;
        
        CourseDAO dao= new CourseDAO();
        
        int PageSize=5;
        int PageNumber=(int)Math.ceil((double)dao.countRow(searchValue,category,(String)request.getParameter("role"))/PageSize);
        request.setAttribute("PageNumber", PageNumber);
//        out.print(PageNumber);
//        out.print(dao.countRow(searchValue,category));
        
        
        Integer currentPage=1;
        if(request.getParameter("currentPage")!=null){currentPage=Integer.parseInt(request.getParameter("currentPage"));}
        request.setAttribute("lastCurrentPage", currentPage);
        
        Integer OFFSET=(currentPage-1)*PageSize;
        

        try {
            ArrayList<CourseDTO> courses= (ArrayList<CourseDTO>)searchCourseByNameAndCategory(searchValue, category,OFFSET,PageSize);
            request.setAttribute("courses", courses);
            if(courses!=null){
                RequestDispatcher rd= request.getRequestDispatcher(url);
                rd.forward(request, response);
            }else{
                out.print("Your data seem null");
                out.print(courses);
            }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
