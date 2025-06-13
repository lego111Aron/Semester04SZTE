package hu.alkfejl.servlet;

import hu.alkfejl.controller.ZeneController;
import hu.alkfejl.model.Zene;
import hu.alkfejl.utils.ConfigManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/api/delete/*")
public class DeleteServlet extends HttpServlet {
    ConfigManager cm = new ConfigManager(Zene.class);

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String urlEnd = req.getPathInfo();
        Zene zene = new Zene();
        zene.setCim(urlEnd.substring(1));

        ZeneController zc = ZeneController.getInstance(Zene.class);

        /*if(zc.delete(zene))
            resp.setStatus(200);
        else
            resp.setStatus(500);*/

    }
}
