<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register - Car Rental System</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<main class="container">
    <div style="max-width: 500px; margin: 3rem auto;">
        <div class="card">
            <div class="card-header">
                <h2 style="margin: 0; border: none; display: block;">Create Account</h2>
                <p style="color: #6b7280; margin-top: 0.5rem;">Join us today and start renting cars</p>
            </div>

            <% String message = (String) request.getAttribute("message");
                if (message != null && !message.isEmpty()) { %>
            <div class="alert alert-danger">
                <%= message %>
            </div>
            <% session.removeAttribute("message"); %>
            <% } %>

            <form action="<%= request.getContextPath() %>/register" method="post" class="card-body">
                <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 1rem;">
                    <div class="form-group">
                        <label for="name">First Name</label>
                        <input type="text" id="name" name="name" placeholder="John" required>
                    </div>

                    <div class="form-group">
                        <label for="surname">Last Name</label>
                        <input type="text" id="surname" name="surname" placeholder="Doe" required>
                    </div>
                </div>

                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" placeholder="Choose a username" required>
                </div>

                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" placeholder="At least 6 characters" required>
                </div>

                <div class="form-group">
                    <label for="role">Account Type</label>
                    <select id="role" name="role" required>
                        <option value="">Select your role</option>
                        <option value="USER">Regular User</option>
                        <option value="ADMIN">Administrator</option>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary" style="width: 100%; padding: 0.85rem; margin-top: 1rem;">
                    Create Account
                </button>
            </form>

            <div style="text-align: center; margin-top: 1.5rem; padding-top: 1.5rem; border-top: 1px solid #e5e7eb;">
                <p style="margin: 0;">Already have an account?
                    <a href="<%= request.getContextPath() %>/login"
                       style="color: #2563eb; text-decoration: none; font-weight: 600;">Sign in here</a>
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
