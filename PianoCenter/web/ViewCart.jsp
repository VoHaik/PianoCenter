<%-- 
    Document   : ViewCart
    Created on : Mar 15, 2025, 1:10:02 PM
    Author     : OS
--%>

<%@page import="dao.CartDAO"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="dto.CourseDTO"%>
<%@page import="dao.CourseDAO"%>
<%@page import="dto.CartDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    CartDAO cartDAO= new CartDAO();
    String username= (String)session.getAttribute("username");
    ArrayList<CartDTO> carts= (ArrayList<CartDTO>)cartDAO.read(username);
    CourseDAO dao = new CourseDAO();
    BigDecimal totalAll=BigDecimal.ZERO;
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%if(username!=null){%>
        <h1>This your cart <%=username%>!</h1>
        <%}else{%>
        
        
        <%}%>
        <%if(carts!=null){%>
            <%if(!carts.isEmpty()){%>
            <form action="MainController" method="POST">
        <table border="1">
            <thead>
                <tr>
                    
                    <th>Course</th>
                    <th>TutionFee</th>
                    <th>Quantity</th>
                    <th>Total</th>
                    
                </tr>
            </thead>
            <tbody>
                
                <%for(CartDTO cart:carts){%> 
                
                <%
                    
                    BigDecimal quantity= BigDecimal.valueOf(cart.getQuantity());
                    BigDecimal tutionFee= dao.getCourse(cart.getCourseID()).getTutionFee();
                    BigDecimal total = tutionFee.multiply(quantity);
                    int quantityOfCourse= dao.getCourse(cart.getCourseID()).getQuantity();
                    totalAll=totalAll.add(total);
                    String errorCode=(String) session.getAttribute(String.valueOf(cart.getCourseID()));
                    String conditionForError =request.getParameter("refreshForError");
                %>
                <tr>
                    
                    <td><%=dao.getCourse(cart.getCourseID()).getName()%></td>
                    <td><%=dao.getCourse(cart.getCourseID()).getTutionFee()%></td>
                    
                        <form action="MainController?from=refreshForError">
                            <td>
                            <input type="text" name="currentQuantity" value="<%=cart.getQuantity()%>"/>
                            <input type="submit" style="display: none" name="btAction" value="updateCart" />
                            <input type="hidden" name="currentCartID" value="<%=cart.getCartID()%>" />
                            
                            </td>
                   
                        
                    
                        </form>
                    
                    <td><%=total%></td>
                    <td><input type="checkbox" name="selectedCarts" value="<%=cart.getCartID()%>" /></td>
                    <%if(errorCode!=null){%>
                    <td><span style="color: red"><%=errorCode%></span></td>
                    <%}%>

                </tr>
                <%}%>
                <%String error = (String)request.getAttribute("error");
                if (error!=null){
                %>
                <tr>
                        <td colspan="3">
                            <span style="font-family: fantasy;color: red"><%=error%></span>
                            
                        </td>

                </tr>


                <%}%>
                <tr>
                        <td colspan="1">
                            <span style="font-family: fantasy;color: #12876f">TOTAL CART MONEY</span>
                            
                        </td>
                        <td colspan="2">
                            <span style="font-family: fantasy;color: black"><%=totalAll%></span>
                            
                        </td>
                        <td colspan="3">
                            <input type="submit" name="btAction" value="Booking" />
                            <input type="submit" value="Remove" name="btAction" />
                        </td>

                        
                </tr>
            </tbody>
            
            <%}%>
            <%}%>
        </table>
        </form>
        <a href="HomePage.jsp">Back to HomePage</a>
    </body>
</html>
