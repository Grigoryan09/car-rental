<%@ page import="java.util.List" %>
<%@ page import="exampleCarRental.model.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customers</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<table border="1">
    <tr>
        <th>Name</th>
        <th>Surname</th>
        <th>License Number</th>
        <th>Phone</th>
        <th>Email</th>
    </tr>

    <% List<Customer> customers = (List<Customer>) request.getAttribute("customers");
        for (Customer customer : customers) {%>
    <tr>
        <td><%= customer.getName() %>
        </td>
        <td><%= customer.getSurname() %>
        </td>
        <td><%= customer.getLicenseNumber() %>
        </td>
        <td><%= customer.getPhone() %>
        </td>
        <td><%= customer.getEmail() %>
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
            <button type="submit"><a href="/addCustomer">Add Customer</a></button>
        </td>
    </tr>
</table>
</body>
</html>
