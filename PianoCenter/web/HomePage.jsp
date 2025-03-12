<%-- 
    Document   : HomePage
    Created on : Mar 12, 2025, 11:58:34 AM
    Author     : OS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        String username=(String)session.getAttribute("username");
    %>
    
    
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome <%=username%>!</h1>
        <div>
            <div class="header">
                
                
            </div>
            <div class="body">
                <div class="">
                    
                    
                </div>
                <div class="search-container">
                    
                    
                </div>
                <div class="result-container">
                    
                </div>
            </div>
            
        </div>
    </body>
</html>
