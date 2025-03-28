package hu.alkfej.dao;

import hu.alkfej.modul.Kutya;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KutyaDAOImpl implements KutyaDAO {

    private static String CONN;
    private static KutyaDAOImpl instance;

    private  KutyaDAOImpl(String conn) {
        CONN = conn;
        try{
            Class.forName("org.sqlite.JDBC");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static KutyaDAOImpl getInstance(String connStr){
        if(instance == null){
            synchronized (KutyaDAOImpl.class){
                if(instance == null){
                    instance = new KutyaDAOImpl(connStr);
                }
            }
        }
        return instance;
    }

    private enum Query{
        INSERT("INSERT INTO kutya (nev, fajta, gazda) VALUES(?,?,?);"),
        SELECT("SELECT * FROM kutya;");
//        FILTER("SELECT * FROM utazas WHERE Uticel = ?;");

        private String command;

        Query(String command) {
            this.command = command;
        }

        public String getCommand() {
            return command;
        }
    }

    @Override
    public boolean add(Kutya kutya) {
        try(Connection c = DriverManager.getConnection(CONN);
            PreparedStatement pst = c.prepareStatement(Query.INSERT.command, Statement.RETURN_GENERATED_KEYS))
        {
            pst.setString(1,kutya.getNev());
            pst.setString(2, kutya.getFajta());
            pst.setString(3, kutya.getGazda());

            int affectedRows = pst.executeUpdate();

            return affectedRows == 1;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Kutya> find(Kutya filter) {
        List<Kutya> result = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(CONN);
            PreparedStatement pst = c.prepareStatement(Query.SELECT.command);
            ResultSet rs = pst.executeQuery())
        {
            while(rs.next()){
                Kutya kutya = new Kutya();
                kutya.setNev(rs.getString("nev"));
                kutya.setFajta(rs.getString("fajta"));
                kutya.setGazda(rs.getString("gazda"));

                result.add(kutya);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
