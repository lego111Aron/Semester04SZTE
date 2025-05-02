package hu.alkfejl.modell;

import javax.swing.text.StyledEditorKit;

public class Mobil {
    private String nev;
    private String gyarto;
    private boolean esim;

    public Mobil(String nev, String gyarto, boolean esim) {
        this.nev = nev;
        this.gyarto = gyarto;
        this.esim = esim;
    }

    public Mobil() {

    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getGyarto() {
        return gyarto;
    }

    public void setGyarto(String gyarto) {
        this.gyarto = gyarto;
    }

    public boolean isEsim() {
        return esim;
    }

    public void setEsim(boolean esim) {
        this.esim = esim;
    }
}
