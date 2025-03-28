package hu.alkfej.controller;

import hu.alkfej.dao.ConfigManager;
import hu.alkfej.dao.KutyaDAO;
import hu.alkfej.dao.KutyaDAOImpl;
import hu.alkfej.modell.Kutya;

import java.util.List;

public class KutyaController {
    private KutyaDAO dao;
    private static KutyaController kutyaController = null;

    public KutyaController() {
        dao = KutyaDAOImpl.getInstance(ConfigManager.getValue("db.url"));
    }

    public static KutyaController getInstance() {
        if (kutyaController == null)
            kutyaController = new KutyaController();
        return kutyaController;
    }

    public boolean add(Kutya kutya) {
        return dao.add(kutya);
    }

    public List<Kutya> find(Kutya kutya) {
        System.out.println("called");
        return dao.find(kutya);
    }
}
