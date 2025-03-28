package hu.alkfejl.controller;

import hu.alkfejl.dao.JegyzokonyvDAO;
import hu.alkfejl.dao.JegyzokonyvDAOImpl;
import hu.alkfejl.dao.util.Column;
import hu.alkfejl.model.Jegyzokonyv;
import hu.alkfejl.util.ConfigManager;

import java.util.List;


public class JegyzokonyvController {

    private JegyzokonyvDAO dao;

    private JegyzokonyvController(){
        dao = JegyzokonyvDAOImpl.getInstance(ConfigManager.getValue("db.url"));
    }


    private static class SingletonHelper{
        private static final JegyzokonyvController INSTANCE = new JegyzokonyvController();
    }

    public static JegyzokonyvController getInstance(){
        return JegyzokonyvController.SingletonHelper.INSTANCE;
    }

    public boolean add(Jegyzokonyv jegyzokonyv){
        return dao.add(jegyzokonyv);
    }

    public List<Jegyzokonyv> filter(Column c, Jegyzokonyv jegyzokonyv){
        return dao.filter(c, jegyzokonyv);
    }


}
