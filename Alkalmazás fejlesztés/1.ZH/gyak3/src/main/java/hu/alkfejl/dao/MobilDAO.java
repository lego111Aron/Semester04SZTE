package hu.alkfejl.dao;

import hu.alkfejl.modell.Mobil;

import java.util.List;

public interface MobilDAO {
//    5. gyakorlatból lehet kimásolni, ez után kell a depedency is
    boolean add(Mobil mobil);
    List<Mobil> find(Mobil mobil);
}
