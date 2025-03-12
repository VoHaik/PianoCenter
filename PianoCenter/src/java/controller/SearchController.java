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
import java.util.ArrayList;
import java.util.List;
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

    private List<CourseDTO> searchCourseByNameAndCategory(String name,String category){
        
        CourseDAO dao= new CourseDAO();
        ArrayList<CourseDTO> courses= (ArrayList<CourseDTO>)dao.read(name);
        if(category.equalsIgnoreCase("all")){return courses;}
        ArrayList<CourseDTO> coursesWC= new ArrayList<>();
        for(CourseDTO dto: courses){
            if(dto.getCategory().equalsIgnoreCase(category)){coursesWC.add(dto);}
        }
        return coursesWC;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url=MainController.homePage;
        try {
           String searchValue=request.getParameter("txtSearch");
           String category=request.getParameter("category");
            ArrayList<CourseDTO> courses= (ArrayList<CourseDTO>)searchCourseByNameAndCategory(searchValue, category);
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
