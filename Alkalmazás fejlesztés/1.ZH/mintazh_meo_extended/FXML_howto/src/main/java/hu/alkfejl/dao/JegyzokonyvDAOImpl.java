package hu.alkfejl.dao;

import hu.alkfejl.dao.util.Column;
import hu.alkfejl.model.Jegyzokonyv;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class JegyzokonyvDAOImpl implements JegyzokonyvDAO{

    private static String CONN_STR;

    private JegyzokonyvDAOImpl(){}

    private static class SingletonHelper{
        private static final JegyzokonyvDAOImpl INSTANCE = new JegyzokonyvDAOImpl();
    }

     static void setConnStr(String str){CONN_STR = str;}

    public static JegyzokonyvDAOImpl getInstance(String connStr){
        setConnStr(connStr);
        return SingletonHelper.INSTANCE;
    }

    @Override
    public boolean add(Jegyzokonyv jegyzokonyv) {
        try(Connection c = DriverManager.getConnection(CONN_STR);
            PreparedStatement stmt = jegyzokonyv.getId() <= 0 ?
                    c.prepareStatement(Query.INSERT.sqlQuery(null), Statement.RETURN_GENERATED_KEYS) :
                    c.prepareStatement(Query.UPDATE.sqlQuery(null))
        ){
            if(jegyzokonyv.getId() > 0){
                stmt.setInt(5,jegyzokonyv.getId());
            }

            stmt.setString(1, jegyzokonyv.getCim());
            stmt.setString(2, jegyzokonyv.getLeiras());
            stmt.setString(3, jegyzokonyv.getDatum());
            stmt.setString(4, jegyzokonyv.getJegyzo());

            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0){
                return false;
            }

            if(jegyzokonyv.getId() < 1){
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()){
                    jegyzokonyv.setId(rs.getInt(1));
                }
            }

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Jegyzokonyv> filter(Column column, Jegyzokonyv jegyzokonyv) {
        List<Jegyzokonyv> result = new LinkedList<>();

        try(Connection c = DriverManager.getConnection(CONN_STR);
            PreparedStatement stmt = c.prepareStatement(Query.SELECT.sqlQuery(column), Statement.RETURN_GENERATED_KEYS);
            )
        {
            switch (column){
                case DATUM:
                    stmt.setString(1, jegyzokonyv.getDatum());
                    break;
                case CIM:
                    stmt.setString(1,jegyzokonyv.getCim());
                    break;
                default:
                    break;
            }

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Jegyzokonyv jk = new Jegyzokonyv();
                jk.setId(rs.getInt("ID"));
                jk.setCim(rs.getString("cim"));
                jk.setLeiras(rs.getString("leiras"));
                jk.setDatum(rs.getString("datum"));
                jk.setJegyzo(rs.getString("jegyzo"));

                result.add(jk);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    private enum Query{
        INSERT {
            @Override
            String sqlQuery(Column column) {
                return "INSERT INTO jegyzokonyv(cim, leiras, datum, jegyzo) VALUES(?,?,?,?);";
            }
        },
        UPDATE{
            @Override
            String sqlQuery(Column column) {
                return "UPDATE jegyzokonyv SET cim = ?, leiras = ?, datum = ?, jegyzo = ? WHERE ID = ?;";
            }
        },
        SELECT{
            @Override
            String sqlQuery(Column column) {
                StringBuilder sb  = new StringBuilder("SELECT * FROM jegyzokonyv");

                switch (column){
                    case CIM:
                        sb.append(" WHERE cim = ?;");
                        break;
                    case DATUM:
                        sb.append(" WHERE datum = ?;");
                        break;
                    default:
                        sb.append(";");
                        break;
                }

                return sb.toString();
            }
        };

        abstract String sqlQuery(Column column);
    }
}
/**
 * create table jegyzokonyv
 * (
 *     ID     integer not null
 *         constraint jegyzokonyv_pk
 *             primary key autoincrement,
 *     cim    TEXT,
 *     leiras TEXT,
 *     datum  TEXT,
 *     jegyzo TEXT
 * );
 */
