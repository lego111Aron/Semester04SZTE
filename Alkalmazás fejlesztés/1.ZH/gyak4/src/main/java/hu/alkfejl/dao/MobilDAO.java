package hu.alkfejl.dao;

import hu.alkfejl.modell.Mobil;

import java.util.List;

public interface MobilDAO {
    boolean add(Mobil mobil);
    List<Mobil> find(Mobil mobil);
}
