
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Customer</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<form action="/addCustomer" method="post">
    <label>Name</label>
    <input type="text" name="name">

    <label>Surname</label>
    <input type="text" name="surname">

    <label>License Number</label>
    <input type="text" name="license_number">

    <label>Email</label>
    <input type="email" name="email">

    <label>Phone</label>
    <input type="text" name="phone">

    <button type="submit">Save</button>
</form>
</body>
</html>
