package hu.alkfejl.dao;

import hu.alkfejl.modell.Mobil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MobilDAOImpl implements MobilDAO {

    private static String CONN;
    private static MobilDAOImpl instance;

    private  MobilDAOImpl(String conn) {
        CONN = conn;
        try{
            Class.forName("org.sqlite.JDBC");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static MobilDAOImpl getInstance(String connStr){
        if(instance == null){
            synchronized (MobilDAOImpl.class){
                if(instance == null){
                    instance = new MobilDAOImpl(connStr);
                }
            }
        }
        return instance;
    }

    @Override
    public boolean add(Mobil mobil) {

        try(Connection c = DriverManager.getConnection(CONN);
            PreparedStatement pst = c.prepareStatement(Query.INSERT.command, Statement.RETURN_GENERATED_KEYS))
        {
            pst.setString(1, mobil.getNev());
            pst.setString(2, mobil.getGyarto());
            pst.setInt(3, mobil.isEsim() ? 1 : 0 );
            int affectedRows = pst.executeUpdate();

            return affectedRows == 1;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Mobil> find(Mobil filter) {
        List<Mobil> result = new ArrayList<>();
        String query = filter.getNev() != null ? Query.FILTER.command : Query.SELECT.command;

        try(Connection c = DriverManager.getConnection(CONN);
            PreparedStatement pst = c.prepareStatement(query)){

            if (filter.getNev() != null) {
                pst.setString(1, filter.getNev());
            }

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Mobil mobil1 = new Mobil();
                    mobil1.setNev(rs.getString("nev"));
                    mobil1.setGyarto(rs.getString("gyarto"));
                    mobil1.setEsim((1 == rs.getInt("esim")));

                    result.add(mobil1);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    private enum Query{
        INSERT("INSERT INTO mobil (nev, gyarto, esim) VALUES(?,?,?);"),
        SELECT("SELECT * FROM mobil;"),
        FILTER("SELECT * FROM mobil WHERE nev = ?;");

        private String command;

        Query(String command) {
            this.command = command;
        }

        public String getCommand() {
            return command;
        }
    }
}
