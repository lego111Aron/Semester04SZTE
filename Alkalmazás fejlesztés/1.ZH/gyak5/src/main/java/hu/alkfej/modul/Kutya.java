package hu.alkfej.modul;

public class Kutya {
    private String nev;
    private String fajta;
    private String gazda;

    public Kutya(String nev, String fajta, String gazda) {
        this.nev = nev;
        this.fajta = fajta;
        this.gazda = gazda;
    }

    public Kutya() {

    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getFajta() {
        return fajta;
    }

    public void setFajta(String fajta) {
        this.fajta = fajta;
    }

    public String getGazda() {
        return gazda;
    }

    public void setGazda(String gazda) {
        this.gazda = gazda;
    }
}
