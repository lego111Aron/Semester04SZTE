package hu.alkfejl.servlet;

import com.google.gson.Gson;
import hu.alkfejl.controller.ZeneController;
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
    ConfigManager cm = new ConfigManager(Zene.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ZeneController zc = ZeneController.getInstance(cm.getClass());
        Gson gson = new Gson();
        Zene zene = gson.fromJson(req.getReader(), Zene.class);

        /*if(zc.update(zene)){
            resp.setStatus(200);
        } else
            resp.setStatus(500);*/
    }
}
