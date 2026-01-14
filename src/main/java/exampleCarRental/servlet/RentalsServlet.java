package exampleCarRental.servlet;

import exampleCarRental.service.RentalService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = ("/rentals"))
public class RentalsServlet extends HttpServlet {

    private final RentalService rentalService =  new RentalService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("rentals",rentalService.getRentalDto());
        req.getRequestDispatcher("rentals.jsp").forward(req,resp);
    }
}
