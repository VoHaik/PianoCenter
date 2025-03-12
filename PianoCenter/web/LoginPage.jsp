<%-- 
    Document   : LoginPage
    Created on : Mar 12, 2025, 10:27:31 AM
    Author     : OS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--<!DOCTYPE html>

To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.

<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form action="MainController" method="POST">
            username: <input type="text" name="txtUsername" value="" /><br>
            password: <input type="password" name="txtPassword" value="" /><br>
            <input type="submit" name="btAction" value="Login" />
            <input type="reset"/>
        </form>
        <form action="RegisterPage.jsp">
            <input type="submit" name="btAction" value="Register" />
        </form>
        
    </body>
</html>-->
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Responsive Login Form HTML CSS | CodingNepal</title>
  <link rel="stylesheet" href="style.css" />
  <!-- Font Awesome CDN link for icons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" />
</head>
        <%
            Boolean invalidLogin=(Boolean)request.getAttribute("invalidLogin");
        %>
<body>
  <div class="wrapper">
    <div class="title"><span>Login Form</span></div>
    <form action="MainController" method="POST">
      <div class="row">
        <i class="fas fa-user"></i>
        <input type="text" name="txtUsername" value="" placeholder="Email or Phone" required />
      </div>
      <div class="row">
        <i class="fas fa-lock"></i>
        <input type="password" placeholder="Password" name="txtPassword" value="" required />
      </div>
        <div>
            <%if(invalidLogin!=null){%>
                <%if(invalidLogin==false){%>
                <span style="color: red">Your username or password is invalid!!!</span>
                <%}%>
            <%}%>
        </div>
      <div class="row button">
        <input type="submit" value="Login" name="btAction" />
      </div>
      <div class="signup-link">Not a member? <a href="MainController?btAction=Register">Signup now</a></div>
    </form>
  </div>
