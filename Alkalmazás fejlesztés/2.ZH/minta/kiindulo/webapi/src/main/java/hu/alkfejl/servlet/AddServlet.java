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
    private final ConfigManager config = new ConfigManager(this.getClass());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Zene zene = new Gson().fromJson(req.getReader(), Zene.class);

        if(req.getCookies() != null) {
            for (Cookie cookie : req.getCookies()) {
                if (cookie.getValue() != null) {
                    cookie.setValue("modded" + cookie.getValue());
                    resp.addCookie(cookie);
                }
            }
        }

        ZeneController zc = ZeneController.getInstance(config.getClass());

        Gson gson = new Gson();
        try {
            var zene = gson.fromJson(req.getReader(), Zene.class);

            boolean jo = zc.add(zene);
            if(jo){
                resp.setStatus(201);
            } else {
                resp.setStatus(500);
            }
        } catch (JsonSyntaxException e) {
            resp.setStatus(400);
        }
    }
}
