package hu.alkfejl.servlet;

import com.google.gson.Gson;
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
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = {"/api/list", "/alista"})
public class GetServlet extends HttpServlet {
    public int count = 1;
    ConfigManager cm = new ConfigManager(Zene.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Zene zene = new Zene();
        zene.setCim(req.getParameter("cim"));
        zene.setEloado(req.getParameter("eloado"));
        int hossz;

        if (req.getParameter("hossz") == null)
            hossz = -1;
        else
            hossz = Integer.parseInt(req.getParameter("hossz"));
        zene.setHossz(hossz);

        ZeneController zc = ZeneController.getInstance(Zene.class);

        var result = zc.find(zene);
        Gson gson = new Gson();
        String json = gson.toJson(result);

        resp.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        resp.setContentType("application/json");
        resp.getWriter().println(json);
        resp.setStatus(200);

        Cookie cookie = new Cookie("request-number", String.valueOf(count++));
        resp.addCookie(cookie);
    }
}
