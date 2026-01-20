package exampleCarRental.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


@WebServlet(value = "/getImage")
public class GetImageServlet extends HttpServlet {

    private static final String IMAGE_PATH = "C:\\Users\\Admin\\IdeaProjects\\car-rental\\upload-images\\";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pictureUrl = req.getParameter("picture_url");
        File file = new File(IMAGE_PATH + pictureUrl);
        if (!file.exists()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        resp.setContentType("image/jpeg");
        resp.setHeader("Content-Type", "image/jpeg");

        ServletOutputStream outputStream = resp.getOutputStream();

        try (InputStream inputStream = new FileInputStream(file)) {
            IOUtils.copy(inputStream, outputStream);
        }
    }
}
