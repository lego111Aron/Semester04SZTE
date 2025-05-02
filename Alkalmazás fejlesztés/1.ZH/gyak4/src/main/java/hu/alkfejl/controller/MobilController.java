package hu.alkfejl.controller;

import hu.alkfejl.dao.ConfigManager;
import hu.alkfejl.dao.MobilDAO;
import hu.alkfejl.dao.MobilDAOImpl;
import hu.alkfejl.modell.Mobil;

import java.util.List;

public class MobilController {
    private MobilDAO dao;
    private static MobilController mobilController = null;

    public MobilController() {
        dao = MobilDAOImpl.getInstance(ConfigManager.getValue("db.url"));
    }

    public static MobilController getInstance() {
        if (mobilController == null)
            mobilController = new MobilController();
        return mobilController;
    }

    public boolean add(Mobil mobil) {
        return dao.add(mobil);
    }

    public List<Mobil> find(Mobil filter) {
        System.out.println("called");
        return dao.find(filter);
    }
}
