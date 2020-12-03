package hardwareverwaltung.model;

import hardwareverwaltung.logic.DataManager;

import java.time.LocalDate;

import static hardwareverwaltung.util.Utilities.isStringEmpty;

public class DruckerBuilder {
    private String seriennummer;
    private String modell;
    private String hersteller;
    private String status;
    private int herstellergarantie;
    private LocalDate lieferdatum;
    private String technologie;
    private boolean farbdruckfunktion;
    private String paperformatmax;
    private Raum raum;

    public DruckerBuilder setSeriennummer(String seriennummer) {
        if(isStringEmpty(seriennummer)) {
            throw new IllegalArgumentException("Seriennummer darf nicht leer sein!");
        }
        this.seriennummer = seriennummer;
        return this;
    }

    public DruckerBuilder setModell(String modell) {
        if(isStringEmpty(modell)) {
            throw new IllegalArgumentException("Modell darf nicht leer sein!");
        }
        this.modell = modell;
        return this;
    }

    public DruckerBuilder setHersteller(String hersteller) {
        if(isStringEmpty(hersteller)) {
            throw new IllegalArgumentException("Hersteller darf nicht leer sein!");
        }
        this.hersteller = hersteller;
        return this;
    }

    public DruckerBuilder setStatus(String status) {
        if(isStringEmpty(status)) {
            throw new IllegalArgumentException("Status darf nicht leer sein!");
        }
        this.status = status;
        return this;
    }

    public DruckerBuilder setHerstellergarantie(String herstellergarantie) {
        if(isStringEmpty(herstellergarantie)) {
            throw new IllegalArgumentException("Herstellergarantie darf nicht leer sein!");
        }
        try {
            return setHerstellergarantie(Integer.parseInt(herstellergarantie));
        } catch(NumberFormatException nfe) {
            throw new IllegalArgumentException("Nur Ganzzahlen in der Herstellergarantie eintragen!");
        }
    }

    public DruckerBuilder setHerstellergarantie(int herstellergarantie) {
        if(herstellergarantie < 0) {
            throw new IllegalArgumentException("Herstellergarantie darf nicht negativ sein!");
        }
        this.herstellergarantie = herstellergarantie;
        return this;
    }

    public DruckerBuilder setLieferdatum(LocalDate lieferdatum) {
        if(lieferdatum == null) {
            throw new IllegalArgumentException("Lieferdatum darf nicht leer sein!");
        }
        this.lieferdatum = lieferdatum;
        return this;
    }

    public DruckerBuilder setTechnologie(String technologie) {
        if(isStringEmpty(technologie)) {
            throw new IllegalArgumentException("Status darf nicht leer sein!");
        }
        this.technologie = technologie;
        return this;
    }

    public DruckerBuilder setFarbdruckfunktion(boolean farbdruckfunktion) {
        this.farbdruckfunktion = farbdruckfunktion;
        return this;
    }

    public DruckerBuilder setPaperformatmax(String paperformatmax) {
        if(isStringEmpty(paperformatmax)) {
            throw new IllegalArgumentException("Max. Papierformat darf nicht leer sein!");
        }
        this.paperformatmax = paperformatmax;
        return this;
    }

    public DruckerBuilder setRaum(Raum raum) {
        if(DataManager.getInstance().findRaumByID(raum.getId()) != null) {
            this.raum = raum;
            return this;
        }
        throw new IllegalArgumentException("Raum " + raum.getId() + "ist nicht in der DB!");
    }

    public Drucker createDrucker() {
        return new Drucker(seriennummer, modell, hersteller, status, herstellergarantie, lieferdatum, raum, technologie, farbdruckfunktion, paperformatmax);
    }
}