<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login - Car Rental System</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<main class="container">
    <div style="max-width: 450px; margin: 4rem auto;">
        <div class="card">
            <div class="card-header">
                <h2 style="margin: 0; border: none; display: block;">Welcome Back</h2>
                <p style="color: #6b7280; margin-top: 0.5rem;">Sign in to your account to continue</p>
            </div>

            <% String message = (String) request.getAttribute("message");
                if (message != null && !message.isEmpty()) { %>
            <div class="alert alert-danger">
                <%= message %>
            </div>
            <% session.removeAttribute("message"); %>
            <% } %>

            <form action="<%= request.getContextPath() %>/login" method="post" class="card-body">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" placeholder="Enter your username" required>
                </div>

                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" placeholder="Enter your password" required>
                </div>

                <button type="submit" class="btn btn-primary" style="width: 100%; padding: 0.85rem;">
                    Sign In
                </button>
            </form>

            <div style="text-align: center; margin-top: 1.5rem; padding-top: 1.5rem; border-top: 1px solid #e5e7eb;">
                <p style="margin: 0;">Don't have an account?
                    <a href="<%= request.getContextPath() %>/register" style="color: #2563eb; text-decoration: none; font-weight: 600;">Create one now</a>
                </p>
            </div>
        </div>
    </div>
</main>

<footer>
    <div class="footer-container">
        <div class="footer-section">
            <h4>🚗 Car Rental</h4>
            <p>Your trusted car rental service</p>
        </div>
        <div class="footer-section">
            <h4>Quick Links</h4>
            <a href="<%= request.getContextPath() %>/">Home</a>
            <a href="<%= request.getContextPath() %>/cars">Available Cars</a>
        </div>
        <div class="footer-section">
            <h4>Contact</h4>
            <p>Email: info@carrental.com</p>
            <p>Phone: +1 (555) 000-0000</p>
        </div>
    </div>
    <div class="footer-bottom">
        <p>&copy; 2026 Car Rental System. All rights reserved.</p>
    </div>
</footer>

</body>
</html>
