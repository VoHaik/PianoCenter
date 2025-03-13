<%-- 
    Document   : HomePage
    Created on : Mar 12, 2025, 11:58:34 AM
    Author     : OS
--%>

<%@page import="dao.CourseDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.CourseDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        String username=(String)session.getAttribute("username");
        ArrayList<CourseDTO> courses= (ArrayList<CourseDTO>)request.getAttribute("courses");
        CourseDAO dao= new CourseDAO();
        ArrayList<String> categories= (ArrayList<String>)dao.getCategory();
        Integer pageNumber= (Integer)request.getAttribute("PageNumber");
        String lastSearchValue="";
        if(request.getParameter("txtSearch")!=null){lastSearchValue=(String)request.getParameter("txtSearch");}
        String lastCategory="All";
        if(request.getParameter("category")!=null){lastCategory=(String)request.getParameter("category");}
        
//        Integer pageNumber= 5;
    %>
    
    
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <%if(username!=null){%>
        <h1>Welcome <%=username%>!!!</h1>
        <%}else{%>
        <h1>Welcome !!!</h1>
        <%}%>
        <form action="MainController">
            <%if(lastSearchValue.isEmpty()){%>
            <input type="text" name="txtSearch" value="" />
            <%}else{%>
            <input type="text" name="txtSearch" value="<%=lastSearchValue%>" />
            <%}%>
            <input type="submit" name="btAction" value="Search" />
            <select name="category">
                <option value="All">All</option>>
                <%if(categories!=null){%>
                    <%
                        int i=0;
                        for(String category: categories){
                     %>
                <option><%=category%></option>
                    <%i++;}%>
                <%}%>
            </select>
        </form>
        <br>
        <%if(username!=null){%>
        <form action="MainController">
            <input type="submit" name="btAction" value="Logout" />
        </form>
        <%}else{%>

        <a href="LoginPage.jsp">Login</a>
        <a href="RegisterPage.jsp">Sign up</a>
        
        <br><br>
        <%}%>
        <br><br>

    <%if(courses!=null){%>
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
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
            <%for(CourseDTO dto:courses){%>
            
                <tr>
            <form action="MainController">
                        <td>
                            <%=dto.getCourseID()%>
                            <input type="hidden" name="courseID" value="<%=dto.getCourseID()%>" />
                        </td>
                        <td>
                            <input type="text" name="courseName" value="<%=dto.getName()%>" />
                        </td>
                        <td>
                            <input type="text" name="des" value="<%=dto.getDescription()%>" />
                        </td>
                        <td>    
                            <input type="text" name="tution" value="<%=dto.getTutionFee()%>" />
                        </td>
                        <td>
                            <input type="text" name="startDate" value="<%=dto.getStartDate()%>" />
                        </td>
                        <td>
                            <input type="text" name="endDate" value="<%=dto.getEndDate()%>" />
                        </td>
                        <td>
                            
                            <select name="updateCategory">
                <option value="<%=dto.getCategory()%>"><%=dto.getCategory()%></option>
                <%if(categories!=null){%>
                    <%
                        int i=0;
                        for(String category: categories){
                     %>
                <option><%=category%></option>
                    <%i++;}%>
                <%}%>
                            </select>
                        </td>
                        <td>
                            <input type="text" name="createDate" value="<%=dto.getCreateDate()%>" />
                        </td>
                        <td>
                            <%=dto.getLastUpdateUser()%>
                            <input type="hidden" name="lastUpdateUser" value="<%=username%>" />
                        </td>
                        <%if(dto.getStatus().equalsIgnoreCase("Active")){%>
                        <td>
                            <input type="checkbox" name="status" value="Active" checked="checked"/>
                        </td>
                        <%}else{%>
                        <td>
                            <input type="checkbox" name="status" value="Inactive" />
                        </td>
                        <%}%>
                        <td>
                            <input type="text" name="quantity" value="<%=dto.getQuantity()%>" />
                        </td>
                        <td><input type="submit" name="btAction" value="Update" /></td>    
                        <input type="hidden" name="txtSearch" value="<%=""%>" />
                        <input type="hidden" name="category" value="<%="All"%>" />
                    </form>    
                </tr>
                <%}%> 
            <%}else{%>
            <h1 style="color: red">No record found!!!</h1>
            <%}%>
            </tbody>
        </table>
            <%for(int i=1;i<=pageNumber;i++){%>
            <a href="SearchController?currentPage=<%=i%>&&txtSearch=<%=lastSearchValue%>&&category=<%=lastCategory%>"><%=i%></a>
            <%}%>
    <%}%>    
    </body>
</html>
