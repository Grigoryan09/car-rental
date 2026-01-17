package exampleCarRental.servlet;

import exampleCarRental.model.RentalStatus;
import exampleCarRental.service.RentalService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/updateRentalStatus")
public class UpdateRentalStatusServlet extends HttpServlet {

    private final RentalService rentalService = new RentalService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("rentalId"));
        RentalStatus rentalStatus = RentalStatus.valueOf(request.getParameter("status"));
        rentalService.updateRentalStatus(id, rentalStatus);
        response.sendRedirect("rentals");

    }
}
