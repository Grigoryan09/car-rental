<%@ page import="exampleCarRental.model.Customer" %>
<%@ page import="exampleCarRental.model.Car" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Rental</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<h2>Add Rental</h2>
<form method="POST" action="/addRental">

    <label>Car</label>
    <select name="carId"><
        <%
            List<Car> cars = (List<Car>) request.getAttribute("cars");
            for (Car car : cars) {
        %>
        <option value="<%= car.getId() %>">
            <%= car.getBrand() %> <%= car.getModel() %>
        </option>
        <% } %>
    </select>

    <label>Customer</label>
    <select name="customerId">
        <%
            List<Customer> customers = (List<Customer>) request.getAttribute("customers");
            for (Customer customer : customers) {
        %>
        <option value="<%= customer.getId() %>">
            <%= customer.getName() %> <%= customer.getSurname() %>
        </option>
        <% } %>
    </select>

    <label>Start Date</label>
    <input type="date" name="startDate">

    <label>End Date</label>
    <input type="date" name="endDate">

    <button type="submit">Save</button>
</form>
</body>
</html>
