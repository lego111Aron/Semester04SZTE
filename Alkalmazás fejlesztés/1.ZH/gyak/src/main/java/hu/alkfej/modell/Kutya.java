package hu.alkfej.modell;

public class Kutya {
    private String nev;
    private String fajta;
    private String gazdi;

    public Kutya(String nev, String fajta, String gazdi) {
        this.nev = nev;
        this.fajta = fajta;
        this.gazdi = gazdi;
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

    public String getGazdi() {
        return gazdi;
    }

    public void setGazdi(String gazdi) {
        this.gazdi = gazdi;
    }
}
