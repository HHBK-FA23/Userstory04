package hardwareverwaltung.model;

import java.time.LocalDate;

public abstract class Hardware {

    protected static int anzahl;
    protected final int id;
    protected String seriennummer;
    protected String modell;
    protected String hersteller;
    protected String status;
    protected int herstellergarantie;
    protected LocalDate lieferdatum;
    protected Raum raum;

    protected Hardware() {
        anzahl++;
        id = anzahl;
        setStatus("ok");
    }

    public Hardware(String seriennummer,
                    String modell,
                    String hersteller,
                    String status,
                    int herstellergarantie,
                    LocalDate lieferdatum,
                    Raum raum) {
        this();
        this.seriennummer = seriennummer;
        this.modell = modell;
        this.hersteller = hersteller;
        this.status = status;
        this.herstellergarantie = herstellergarantie;
        this.lieferdatum = lieferdatum;
        this.raum = raum;
    }

    //TODO reorder Methods
    public void setStatus(String status) {
        this.status = status;
    }

    public static int getAnzahl() {
        return anzahl;
    }

    public int getId() {
        return id;
    }

    public String getSeriennummer() {
        return seriennummer;
    }

    public String getModell() {
        return modell;
    }

    public String getHersteller() {
        return hersteller;
    }

    public String getStatus() {
        return status;
    }

    public int getHerstellergarantie() {
        return herstellergarantie;
    }

    public LocalDate getLieferdatum() {
        return lieferdatum;
    }

    @Deprecated
    protected LocalDate berechneGarantieende() {
        return getLieferdatum().plusMonths(getHerstellergarantie());
    }

    public String toString() {
        return ""
                + getId() + ";"
                + getSeriennummer() + ";"
                + getModell() + ";"
                + getHersteller() + ";"
                + getStatus() + ";"
                + getHerstellergarantie() + ";"
                + getLieferdatum() + ";"
                + getRaum().getId() + ";"
                + toStringExtraParams();
    }

    protected abstract String toStringExtraParams();

    public Raum getRaum() {
        return raum;
    }


    public void setSeriennummer(String seriennummer) {
        this.seriennummer = seriennummer;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    public void setHerstellergarantie(int herstellergarantie) {
        this.herstellergarantie = herstellergarantie;
    }

    public void setLieferdatum(LocalDate lieferdatum) {
        this.lieferdatum = lieferdatum;
    }

    public void setRaum(Raum raum) {
        this.raum = raum;
    }
}
