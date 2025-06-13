package hu.alkfejl.servlet;

import hu.alkfejl.dao.UtazasDAO;
import hu.alkfejl.model.Utazas;
import hu.alkfejl.utils.ConfigManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/api/find"})
public class GetServlet extends HttpServlet {
    public final ConfigManager cm = new ConfigManager(UtazasDAO.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utazas utazas = new Utazas();

        String nev = req.getParameter("nev");
        String uticel = req.getParameter("uticel");
        String felpanzio = req.getParameter("felpanzio");


    }
}
