package hu.alkfej.dao;

import hu.alkfej.modell.Kutya;

import java.util.List;

public interface KutyaDAO {
    boolean add(Kutya kutya);
    List<Kutya> find(Kutya kutya);
}
