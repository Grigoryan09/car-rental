package exampleCarRental.servlet;

import exampleCarRental.model.Customer;
import exampleCarRental.service.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;


@WebServlet(value = "/addCustomer")
@MultipartConfig(maxFileSize = 50 * 1024 * 1024,
        maxRequestSize = 100 * 1024 * 1024,
        fileSizeThreshold = 1024 * 1024)
public class AddCustomerServlet extends HttpServlet {

    private final CustomerService customerService = new CustomerService();

    private static final String IMAGE_PATH = "C:\\Users\\Admin\\IdeaProjects\\car-rental\\upload-images\\";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/add-customer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Part pic = req.getPart("picture_url");
        String pictureName = System.currentTimeMillis() + "_" + pic.getSubmittedFileName();
        pic.write(IMAGE_PATH + pictureName);

        Customer customer = new Customer();
        customer.setName(req.getParameter("name"));
        customer.setSurname(req.getParameter("surname"));
        customer.setLicenseNumber(req.getParameter("license_number"));
        customer.setEmail(req.getParameter("email"));
        customer.setPhone(req.getParameter("phone"));
        customer.setPictureUrl(pictureName);


        customerService.addCustomer(customer);
        resp.sendRedirect("/customers");

    }
}
