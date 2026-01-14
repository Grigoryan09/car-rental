package exampleCarRental.servlet;

import exampleCarRental.model.Rental;
import exampleCarRental.service.CarService;
import exampleCarRental.service.CustomerService;
import exampleCarRental.service.RentalService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(value = "/addRental")
public class AddRentals extends HttpServlet {


    private final CarService carService = new CarService();
    private final RentalService rentalService = new RentalService();
    private final CustomerService customerService = new CustomerService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.setAttribute("cars", carService.getAlLCar());
       req.setAttribute("customers", customerService.getAllCustomer());
       req.getRequestDispatcher("add-rentals.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Rental rental = new Rental();
        rental.setCarId(Integer.parseInt(req.getParameter("carId")));
        rental.setCustomerId(Integer.parseInt(req.getParameter("customerId")));
        rental.setStartDate(LocalDate.parse(req.getParameter("startDate")));
        rental.setEndDate(LocalDate.parse(req.getParameter("endDate")));
        rentalService.addRental(rentalService.updateTotalCost(rental));

        resp.sendRedirect("/rentals");
    }
}
