package hu.alkfejl.servlet;

import com.google.gson.Gson;
import hu.alkfejl.model.Zene;
import hu.alkfejl.controller.ZeneController;

import hu.alkfejl.utils.ConfigManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = {"/api/list","/api/alista"})
public class GetServlet extends HttpServlet {
    private static int count = 1;
    ConfigManager config = new ConfigManager(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Zene zene = new Zene();

        String cim = req.getParameter("cim");
        zene.setCim(cim);
        String eloado = req.getParameter("eloado");
//        zene.setEloado(req.getParameter("eloado"));
        zene.setEloado(eloado);
        int hossz = 0;
        if(req.getParameter("hossz") == null) {
            hossz = -1;
        } else hossz = Integer.parseInt(req.getParameter("hossz"));
//        int hossz = Integer.parseInt(req.getParameter("hossz"));
        zene.setHossz(hossz);

        ZeneController zc = ZeneController.getInstance(config.getClass());

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
