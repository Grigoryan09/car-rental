package exampleCarRental.servlet;

import exampleCarRental.service.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/customers")
public class CustomersServlet extends HttpServlet {

    CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Object user = req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");;
        }else {
            req.setAttribute("customers",customerService.getAllCustomer());
            req.getRequestDispatcher("WEB-INF/customers.jsp").forward(req,resp);
        }


    }
}
