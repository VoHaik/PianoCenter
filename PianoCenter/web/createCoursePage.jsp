<%-- 
    Document   : createCoursePage
    Created on : Mar 14, 2025, 11:15:46 AM
    Author     : dinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create Course</title>
</head>
<body>

<%  
    Boolean invalidName = (Boolean) request.getAttribute("invalidName");
    Boolean invalidDescription = (Boolean) request.getAttribute("invalidDescription");
    Boolean invalidTuitionFee = (Boolean) request.getAttribute("invalidTuitionFee");
    Boolean invalidDates = (Boolean) request.getAttribute("invalidDates");
    Boolean invalidCategory = (Boolean) request.getAttribute("invalidCategory");
    Boolean invalidStatus = (Boolean) request.getAttribute("invalidStatus");
    Boolean invalidQuantity = (Boolean) request.getAttribute("invalidQuantity");


    String name = request.getParameter("txtCourseName");
    String description = request.getParameter("txtCourseDescription");
    String tuitionFee = request.getParameter("txtTuitionFee");
    String startDate = request.getParameter("txtStartDate");
    String endDate = request.getParameter("txtEndDate");
    String category = request.getParameter("txtCategory");
    String status = request.getParameter("txtStatus");
    String lastUpdateUser = request.getParameter("txtLastUpdateUser");

    // Xử lý giá trị mặc định cho các trường input
    if (name == null) name = "";
    if (description == null) description = "";
    if (tuitionFee == null) tuitionFee = "";
    if (startDate == null) startDate = "";
    if (endDate == null) endDate = "";
    if (category == null) category = "";
    if (status == null) status = "";
    if (lastUpdateUser == null) lastUpdateUser = "";

    // Kiểm tra và xử lý số lượng
    int quan = 0;
    String quantityStr = request.getParameter("txtQuantity");
    if (quantityStr != null && !quantityStr.isEmpty()) {
        try {
            quan = Integer.parseInt(quantityStr);
            if (quan < 0) {
                invalidQuantity = true;
            }
        } catch (NumberFormatException e) {
            invalidQuantity = true;
        }
    }
%>

<form action="MainController">
    <label>Course Name:</label>
    <input type="text" name="txtCourseName" value="<%= name %>" />
    <% if (invalidName != null && !invalidName) { %>
        <span style="color: red">Invalid course name!</span>
    <% } %>
    <br>

    <label>Description:</label>
    <textarea name="txtCourseDescription"><%= description %></textarea>
    <% if (invalidDescription != null && !invalidDescription) { %>
        <span style="color: red">Invalid description!</span>
    <% } %>
    <br>

    <label>Tuition Fee:</label>
    <input type="text" name="txtTuitionFee" value="<%= tuitionFee %>" />
    <% if (invalidTuitionFee != null && !invalidTuitionFee) { %>
        <span style="color: red">Invalid tuition fee!</span>
    <% } %>
    <br>

    <label>Start Date:</label>
    <input type="date" name="txtStartDate" value="<%= startDate %>" />
    <br>

    <label>End Date:</label>
    <input type="date" name="txtEndDate" value="<%= endDate %>" />
    <% if (invalidDates != null && !invalidDates) { %>
        <span style="color: red">Invalid date range! (End Date must be after Start Date)</span>
    <% } %>
    <br>

    <label>Category:</label>
    <input type="text" name="txtCategory" value="<%= category %>" />
    <% if (invalidCategory != null && !invalidCategory) { %>
        <span style="color: red">Invalid category!</span>
    <% } %>
    <br>

    <label>Status:</label>
    <select name="txtStatus">
        <option value="Active" <%= "Active".equals(status) ? "selected" : "" %>>Active</option>
        <option value="Inactive" <%= "Inactive".equals(status) ? "selected" : "" %>>Inactive</option>
    </select>
    <% if (invalidStatus != null && !invalidStatus) { %>
        <span style="color: red">Invalid status!</span>
    <% } %>
    <br>

    <label>Quantity:</label>
    <input type="number" name="txtQuantity" value="<%= quan %>" min="0" />
    <% if (invalidQuantity != null && invalidQuantity) { %>
        <span style="color: red">Invalid quantity! (Must be a valid number & ≥ 0)</span>
    <% } %>
    <br>

    
    <input type="hidden" name="txtLastUpdateUser" value="<%= session.getAttribute("username") %>" />
    <br>


    <input type="submit" name="btAction" value="createCourse" />
    <input type="reset" />
</form>
    <a href="HomePage.jsp">Go back to homepage</a>
</body>
</html>

