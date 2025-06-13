package hu.alkfejl.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import hu.alkfejl.controller.ZeneController;
import hu.alkfejl.model.Zene;
import hu.alkfejl.utils.ConfigManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/api/add"})
public class AddServlet extends HttpServlet {
    ConfigManager cm = new ConfigManager(Zene.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getCookies() != null) {
            for(Cookie cookie : req.getCookies()) {
                cookie.setValue("modded" + cookie.getValue());
                resp.addCookie(cookie);
            }
        }

        ZeneController zc = ZeneController.getInstance(Zene.class);
        Gson gson = new Gson();
        var zene = gson.fromJson(req.getReader(), Zene.class);

        try {
            if(zc.add(zene))
                resp.setStatus(201);
            else
                resp.setStatus(500);
        } catch (JsonSyntaxException e) {
            resp.setStatus(400);
        }
    }
}
