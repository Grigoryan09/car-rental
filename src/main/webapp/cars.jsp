<%@ page import="java.util.List" %>
<%@ page import="exampleCarRental.model.Car" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cars</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<table border="1">
    <tr>
        <th>Brand</th>
        <th>Model</th>
        <th>Year</th>
        <th>Daily Rate</th>
        <th>Status</th>
    </tr>
    <% List<Car> cars = (List<Car>) request.getAttribute("allCars");
        for (Car car : cars) { %>
    <tr>
        <td><%= car.getBrand() %>
        </td>
        <td><%= car.getModel() %>
        </td>
        <td><%= car.getYear() %>
        </td>
        <td><%= car.getDailyRate() %>
        </td>
        <td><%= car.getStatus() %>
        </td>
    </tr>
    <%}%>
</table>
<table>
    <tr>
        <th>Action</th>
    </tr>
    <tr>
        <td>
            <button type="submit"><a href="/addcar">Add Car</a></button>
        </td>
    </tr>
</table>
</body>
</html>
