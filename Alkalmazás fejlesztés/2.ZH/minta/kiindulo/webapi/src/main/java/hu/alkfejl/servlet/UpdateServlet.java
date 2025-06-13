package hu.alkfejl.servlet;

import com.google.gson.Gson;
import hu.alkfejl.controller.ZeneController;
import hu.alkfejl.dao.ZeneDAO;
import hu.alkfejl.model.Zene;
import hu.alkfejl.utils.ConfigManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/api/update"})
public class UpdateServlet extends HttpServlet {
    private final ConfigManager cm = new ConfigManager(ZeneDAO.class);

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Zene zene = new Gson().fromJson(req.getReader(), Zene.class);
//        ZeneController zc = ZeneController.getInstance(cm.getValue("dao"), cm.getValue("db.url"));
        ZeneController zc = ZeneController.getInstance(cm.getClass());

//        if (zc.update(utazas)) {
//
//        }
    }
}
