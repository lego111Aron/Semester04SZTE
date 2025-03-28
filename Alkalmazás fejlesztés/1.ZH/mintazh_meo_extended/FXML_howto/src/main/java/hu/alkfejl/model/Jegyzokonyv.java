package hu.alkfejl.model;

public class Jegyzokonyv {

    private String cim, leiras, datum, jegyzo;

    private int id;

    public Jegyzokonyv() {

        cim = leiras = datum = jegyzo = "";
        id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getJegyzo() {
        return jegyzo;
    }

    public void setJegyzo(String jegyzo) {
        this.jegyzo = jegyzo;
    }

    @Override
    public String toString() {
        return "Jegyzokonyv{" +
                "cim='" + cim + '\'' +
                ", leiras='" + leiras + '\'' +
                ", datum='" + datum + '\'' +
                ", jegyzo='" + jegyzo + '\'' +
                ", id=" + id +
                '}';
    }
}
