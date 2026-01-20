<%@ page import="exampleCarRental.model.User" %>
<%@ page import="exampleCarRental.model.UserRole" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home - Car Rental System</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<main class="container" style="padding: 3rem 2rem;">
    <% User user = (User) session.getAttribute("user"); %>

    <% if (user != null) { %>

    <div style="margin-bottom: 3rem;">
        <h2>🏠 Welcome, <%= user.getUsername() %>👋</h2>
        <p style="color: #6b7280; font-size: 1.1rem;">Manage your car rental business with ease</p>
    </div>

    <div class="stats">
        <div class="stat-card">
            <div class="stat-number">24</div>
            <div class="stat-label">Available Cars</div>
        </div>
        <div class="stat-card secondary">
            <div class="stat-number">156</div>
            <div class="stat-label">Total Customers</div>
        </div>
        <div class="stat-card success">
            <div class="stat-number">42</div>
            <div class="stat-label">Active Rentals</div>
        </div>
        <div class="stat-card danger">
            <div class="stat-number">$12,450</div>
            <div class="stat-label">Monthly Revenue</div>
        </div>
    </div>

    <div style="margin-top: 3rem; margin-bottom: 3rem;">
        <h3>⚡ Quick Actions</h3>
        <div class="grid" style="grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));">
            <a href="<%= request.getContextPath() %>/cars" class="card"
               style="text-decoration: none; color: inherit; transition: all 0.3s;">
                <div style="font-size: 2.5rem; margin-bottom: 1rem;">🚗</div>
                <h4 style="margin: 0 0 0.5rem 0;">View Cars</h4>
                <p style="margin: 0; color: #6b7280;">Manage vehicle fleet</p>
            </a>

            <a href="<%= request.getContextPath() %>/customers" class="card"
               style="text-decoration: none; color: inherit; transition: all 0.3s;">
                <div style="font-size: 2.5rem; margin-bottom: 1rem;">👥</div>
                <h4 style="margin: 0 0 0.5rem 0;">Customers</h4>
                <p style="margin: 0; color: #6b7280;">Manage client database</p>
            </a>

            <a href="<%= request.getContextPath() %>/rentals" class="card"
               style="text-decoration: none; color: inherit; transition: all 0.3s;">
                <div style="font-size: 2.5rem; margin-bottom: 1rem;">📋</div>
                <h4 style="margin: 0 0 0.5rem 0;">Rentals</h4>
                <p style="margin: 0; color: #6b7280;">View all rental bookings</p>
            </a>

            <a href="<%= request.getContextPath() %>/addRental" class="card"
               style="text-decoration: none; color: inherit; transition: all 0.3s;">
                <div style="font-size: 2.5rem; margin-bottom: 1rem;">➕</div>
                <h4 style="margin: 0 0 0.5rem 0;">New Rental</h4>
                <p style="margin: 0; color: #6b7280;">Create new booking</p>
            </a>
        </div>
    </div>
    <%if (user.getRole() == UserRole.ADMIN) {%>
    <div style="background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; padding: 2rem; border-radius: 12px; text-align: center;">
        <h3 style="margin-top: 0;">Ready to grow your business?</h3>
        <p>Manage your entire fleet efficiently with our Car Rental System</p>
        <div style="margin-top: 1.5rem;">
            <a href="<%= request.getContextPath() %>/addcar" class="btn btn-secondary" style="margin-right: 1rem;">Add
                New Car</a>
            <a href="<%= request.getContextPath() %>/addCustomer" class="btn btn-secondary">Register Customer</a>
        </div>
    </div>
    <%}%>
    <div style="text-align: center; margin-top: 2rem;">
        <a href="<%= request.getContextPath() %>/logout"
           style="color: #ef4444; text-decoration: none; font-weight: 600;">
            🚪 Logout
        </a>
    </div>

    <% } else { %>

    <div style="max-width: 600px; margin: 4rem auto; text-align: center;">
        <div style="font-size: 4rem; margin-bottom: 1rem;">🚗</div>
        <h1>Welcome to Car Rental System</h1>
        <p style="font-size: 1.1rem; color: #6b7280; margin: 1rem 0 2rem;">
            Manage your fleet, customers, and rentals all in one place
        </p>
        <div style="display: flex; gap: 1rem; justify-content: center;">
            <a href="<%= request.getContextPath() %>/login" class="btn btn-primary" style="padding: 0.85rem 2rem;">
                Sign In
            </a>
            <a href="<%= request.getContextPath() %>/register" class="btn btn-secondary" style="padding: 0.85rem 2rem;">
                Create Account
            </a>
        </div>
    </div>

    <% } %>
</main>

<footer>
    <div class="footer-container">
        <div class="footer-section">
            <h4>🚗 Car Rental</h4>
            <p>Your trusted car rental service</p>
        </div>
        <div class="footer-section">
            <h4>Services</h4>
            <a href="#">Fleet Management</a>
            <a href="#">Customer Management</a>
            <a href="#">Booking System</a>
        </div>
        <div class="footer-section">
            <h4>Support</h4>
            <a href="#">Help Center</a>
            <a href="#">Documentation</a>
            <a href="#">Contact Us</a>
        </div>
    </div>
    <div class="footer-bottom">
        <p>&copy; 2026 Car Rental System. All rights reserved.</p>
    </div>
</footer>

</body>
</html>

