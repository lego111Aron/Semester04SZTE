package hu.alkfejl.dao;

import hu.alkfejl.dao.util.Column;
import hu.alkfejl.model.Jegyzokonyv;

import java.util.List;

public interface JegyzokonyvDAO {

    boolean add(Jegyzokonyv jegyzokonyv);
    List<Jegyzokonyv> filter(Column column, Jegyzokonyv jegyzokonyv);



}
