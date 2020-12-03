package hardwareverwaltung.model;

import hardwareverwaltung.util.AlertWindow;

import java.time.LocalDate;

public class Drucker extends Hardware {

    protected String technologie;
    protected boolean farbdruckfunktion;
    protected String paperformatmax;
    protected int druckseitengesamt;
    protected int restkapazitaet;
    protected int kapazitaetbetriebsmittel;

    public Drucker() {
        super();
        setKapazitaetbetriebsmittel(200);
        setRestkapazitaet(200);
    }

    public Drucker(String seriennummer,
                   String modell,
                   String hersteller,
                   String status,
                   int herstellergarantie,
                   LocalDate lieferdatum,
                   Raum raum,
                   String technologie,
                   boolean farbdruckfunktion,
                   String paperformatmax) {
        this();
        this.seriennummer = seriennummer;
        this.modell = modell;
        this.hersteller = hersteller;
        this.status = status;
        this.herstellergarantie = herstellergarantie;
        this.lieferdatum = lieferdatum;
        this.raum = raum;
        this.technologie = technologie;
        this.farbdruckfunktion = farbdruckfunktion;
        this.paperformatmax = paperformatmax;
    }


    public String getTechnologie() {
        return technologie;
    }

    public boolean isFarbdruckfunktion() {
        return farbdruckfunktion;
    }

    public String getPaperformatmax() {
        return paperformatmax;
    }

    public int getDruckseitengesamt() {
        return druckseitengesamt;
    }

    public void setDruckseitengesamt(int druckseitengesamt) {
        this.druckseitengesamt = druckseitengesamt;
    }

    public int getRestkapazitaet() {
        return restkapazitaet;
    }

    public void setRestkapazitaet(int restkapazitaet) {
        this.restkapazitaet = restkapazitaet;
    }

    public int getKapazitaetbetriebsmittel() {
        return kapazitaetbetriebsmittel;
    }

    public void setKapazitaetbetriebsmittel(int kapazitaetbetriebsmittel) {
        this.kapazitaetbetriebsmittel = kapazitaetbetriebsmittel;
    }

    @Override
    public String toStringExtraParams() {
        return ""
                + getTechnologie() + ";"
                + isFarbdruckfunktion() + ";"
                + getPaperformatmax() + ";"
                + getDruckseitengesamt() + ";"
                + getRestkapazitaet() + ";"
                + getKapazitaetbetriebsmittel()
        ;
    }

    /**
     * Wechsle Betriebsmittel setzt die Restkapazität und KapazitaetBetriebsmittel
     * auf den übergebendem Wert Kapaztaet.
     * @param kapazitaet die zu setzende Kapazitaet
     */
    public void wechsleBetriebsmittel(int kapazitaet) {
        if(kapazitaet <= 0) {
            AlertWindow.createErrorWindow("Kapazität muss größer als 0 sein!");
        } else {
            this.restkapazitaet = kapazitaet;
            this.kapazitaetbetriebsmittel = kapazitaet;
        }


    }

    /**
     * Drucken druckt die Anzahl seiten die Übergeben wird.
     * Wenn die Kapaztaet nicht ausreicht, druckt er nur so viele Seiten, wie möglich ist.
     * @param anzahlseiten die Anzahl an Seiten die gedruckt werden sollen.
     */
    public void drucken(int anzahlseiten) {
        if(anzahlseiten <= 0) {
            AlertWindow.createErrorWindow("Anzahl Seiten muss größer 0 sein!");
        } else {
            if(anzahlseiten > getRestkapazitaet()) {
                String s = "Es konnten nur " + getRestkapazitaet() + " Seiten gedruckt werden!\n" +
                        "Bitte wechseln Sie das Betriebsmittel!";

                setDruckseitengesamt(getDruckseitengesamt() + getRestkapazitaet());
                setRestkapazitaet(0);

                AlertWindow.createErrorWindow(s);
            } else {
                setRestkapazitaet(getRestkapazitaet() - anzahlseiten);
                setDruckseitengesamt(getDruckseitengesamt() + anzahlseiten);
                AlertWindow.createErrorWindow("" + anzahlseiten + " Seiten erfolgreich gedruckt!");
            }
        }
    }

    public void setTechnologie(String technologie) {
        this.technologie = technologie;
    }

    public void setFarbdruckfunktion(boolean farbdruckfunktion) {
        this.farbdruckfunktion = farbdruckfunktion;
    }

    public void setPaperformatmax(String paperformatmax) {
        this.paperformatmax = paperformatmax;
    }
}
