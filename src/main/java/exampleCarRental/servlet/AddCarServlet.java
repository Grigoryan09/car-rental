package exampleCarRental.servlet;

import exampleCarRental.model.Car;
import exampleCarRental.service.CarService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(value = "/addcar")
public class AddCarServlet extends HttpServlet {

    private CarService carService =  new CarService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("add-car.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Car car = new Car();
        car.setBrand(req.getParameter("brand"));
        car.setModel(req.getParameter("model"));
        car.setYear(Integer.parseInt(req.getParameter("year")));
        car.setDailyRate(new BigDecimal(req.getParameter("dailyRate")));
        carService.addCar(car);
        resp.sendRedirect("/cars");
    }
}
