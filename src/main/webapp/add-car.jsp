<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>/Add Car</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<form action="/addcar" method="post">
    Brand <input type="text" name="brand" placeholder="Brand Name"><br>
    Model<input type="text" name="model" placeholder="Model Name"><br>
    Year<input type="number" name="year" placeholder="Year"><br>
    DailyRate<input type="number" name="dailyRate" placeholder="Daily Rate"><br>
    <input type="submit" value="Add Car">
</form>
</body>
</html>
