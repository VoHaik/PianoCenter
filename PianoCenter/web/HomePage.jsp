<%-- 
    Document   : HomePage
    Created on : Mar 12, 2025, 11:58:34 AM
    Author     : OS
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="dto.CourseDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        String username=(String)session.getAttribute("username");
        ArrayList<CourseDTO> courses= (ArrayList<CourseDTO>)request.getAttribute("courses");
    %>
    
    
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%if(username!=null){%>
        <h1>Welcome <%=username%>!!!</h1>
        <%}else{%>
        <h1>Welcome !!!</h1>
        <%}%>
        <form action="MainController">
            <input type="text" name="txtSearch" value="" />
            <input type="submit" name="btAction" value="Search" />
        </form>
        <br><br>


    <%if(!courses.isEmpty()){%>    
        <table border="1">
            <thead>
                <tr>
                    <th>CourseID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>TutionFee</th>
                    <th>StartDate</th>
                    <th>EndDate</th>
                    <th>Category</th>
                    <th>CreateDate</th>
                    <th>LastUpdateUser</th>
                    <th>Status</th>
                    <th>Quantity</th>
                </tr>
            </thead>
            <tbody>
            <%for(CourseDTO dto:courses){%>
                <tr>
                    <td><%=dto.getCourseID()%></td>
                    <td><%=dto.getName()%></td>
                    <td><%=dto.getDescription()%></td>
                    <td><%=dto.getTutionFee()%></td>
                    <td><%=dto.getStartDate()%></td>
                    <td><%=dto.getEndDate()%></td>
                    <td><%=dto.getCategory()%></td>
                    <td><%=dto.getCreateDate()%></td>
                    <td><%=dto.getLastUpdateUser()%></td>
                    <td><%=dto.getStatus()%></td>
                    <td><%=dto.getQuantity()%></td>
                </tr>
            <%}%>
            </tbody>
        </table>
    <%}%>    
    </body>
</html>
