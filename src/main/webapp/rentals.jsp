<%@ page import="exampleCarRental.dto.RentalDto" %>
<%@ page import="java.util.List" %>
<%@ page import="exampleCarRental.model.RentalStatus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rentals</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<h2>Rentals</h2>
<a href = "/addRental">Add Rental</a>
<table border="1">
    <tr>
        <th>Car Information</th>
        <th>Customer Name</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Total Cost</th>
        <th>Status</th>
        <th>Change Status</th>
    </tr>

    <%
       List<RentalDto> rentals = (List<RentalDto>) request.getAttribute("rentals");
        for (RentalDto rental : rentals) {
    %>
    <tr>
        <td><%=rental.getCar().getBrand()%> <%=rental.getCar().getModel()%></td>
        <td><%=rental.getCustomer().getName()%></td>
        <td><%=rental.getRental().getStartDate()%></td>
        <td><%=rental.getRental().getEndDate()%></td>
        <td><%=rental.getRental().getTotalCost()%></td>
        <td><%=rental.getRental().getRentalStatus()%></td>

        <td>
            <% if (rental.getRental().getRentalStatus() == RentalStatus.ACTIVE) { %>
            <form method="post" action="/updateRentalStatus">
                <input type="hidden" name="rentalId" value="<%= rental.getRental().getId() %>">
                <input type="hidden" name="status" value="CANCELLED">
                <button type="submit">Rent</button>
            </form>
            <% } else { %>
            <form method="post" action="/updateRentalStatus">
                <input type="hidden" name="rentalId" value="<%= rental.getRental().getId() %>">
                <input type="hidden" name="status" value="ACTIVE">
                <button type="submit">Return</button>
            </form>
            <% } %>
        </td>
    </tr>
    <% } %>
</table>
<table>
    <tr>
        <th>Action</th>
    </tr>
    <tr>
        <td>
            <button type="submit"><a href="/addRental">Add Rental</a></button>
        </td>
    </tr>
</table>
</body>
</html>
