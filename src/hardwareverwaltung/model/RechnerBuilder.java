package hardwareverwaltung.model;

import hardwareverwaltung.logic.DataManager;

import java.time.LocalDate;

import static hardwareverwaltung.util.Utilities.isStringEmpty;

public class RechnerBuilder {
    private String seriennummer;
    private String modell;
    private String hersteller;
    private String status;
    private int herstellergarantie;
    private LocalDate lieferdatum;
    private String cpu;
    private int arbeitsspeicher;
    private String betriebssystem;
    private String typ;
    private String grafikkarte;
    private int festplatteSSD;
    private int festplatteHDD;
    private Raum raum;

    public RechnerBuilder setSeriennummer(String seriennummer) {
        if(isStringEmpty(seriennummer)) {
            throw new IllegalArgumentException("Seriennnummer darf nicht leer sein!");
        }
        this.seriennummer = seriennummer;
        return this;
    }

    public RechnerBuilder setModell(String modell) {
        if(isStringEmpty(modell)) {
            throw new IllegalArgumentException("Modell darf nicht leer sein!");
        }
        this.modell = modell;
        return this;
    }

    public RechnerBuilder setHersteller(String hersteller) {
        if(isStringEmpty(hersteller)) {
            throw new IllegalArgumentException("Hersteller darf nicht leer sein!");
        }
        this.hersteller = hersteller;
        return this;
    }

    public RechnerBuilder setStatus(String status) {
        if(isStringEmpty(status)) {
            throw new IllegalArgumentException("Status darf nicht leer sein!");
        }
        this.status = status;
        return this;
    }

    public RechnerBuilder setHerstellergarantie(String herstellergarantie) {
        if(isStringEmpty(herstellergarantie)) {
            throw new IllegalArgumentException("Herstellergarantie darf nicht leer sein!");
        }
        try {
            return setHerstellergarantie(Integer.parseInt(herstellergarantie));
        } catch(NumberFormatException nfe) {
            throw new IllegalArgumentException("Nur Ganzzahlen in der Herstellergarantie eintragen!");
        }
    }

    public RechnerBuilder setHerstellergarantie(int herstellergarantie) {
        if(herstellergarantie < 0) {
            throw new IllegalArgumentException("Herstellergarantie darf nicht negativ sein!");
        }
        this.herstellergarantie = herstellergarantie;
        return this;
    }

    public RechnerBuilder setLieferdatum(LocalDate lieferdatum) {
        if(lieferdatum == null) {
            throw new IllegalArgumentException("Lieferdatum darf nicht leer sein!");
        }
        this.lieferdatum = lieferdatum;
        return this;
    }

    public RechnerBuilder setCpu(String cpu) {
        if(isStringEmpty(cpu)) {
            throw new IllegalArgumentException("CPU darf nicht leer sein!");
        }
        this.cpu = cpu;
        return this;
    }

    public RechnerBuilder setArbeitsspeicher(String arbeitsspeicher) {
        if(isStringEmpty(arbeitsspeicher)) {
            throw new IllegalArgumentException("Arbeitsspeicher darf nicht leer sein!");
        }
        try {
            return setArbeitsspeicher(Integer.parseInt(arbeitsspeicher));
        } catch(NumberFormatException nfe) {
            throw new IllegalArgumentException("Nur Ganzzahlen bei Arbeitsspeicher eintragen!");
        }
    }

    public RechnerBuilder setArbeitsspeicher(int arbeitsspeicher) {
        if(arbeitsspeicher < 0) {
            throw new IllegalArgumentException("Arbeitsspeicher darf nicht negativ sein!");
        }
        this.arbeitsspeicher = arbeitsspeicher;
        return this;
    }



    public RechnerBuilder setBetriebssystem(String betriebssystem) {
        if(isStringEmpty(betriebssystem)) {
            throw new IllegalArgumentException("Betriebssystem darf nicht leer sein!");
        }
        this.betriebssystem = betriebssystem;
        return this;
    }

    public RechnerBuilder setTyp(String typ) {
        if(isStringEmpty(typ)) {
            throw new IllegalArgumentException("Typ darf nicht leer sein!");
        }
        this.typ = typ;
        return this;
    }

    public RechnerBuilder setGrafikkarte(String grafikkarte) {
        if(isStringEmpty(grafikkarte)) {
            throw new IllegalArgumentException("Grafikkarte darf nicht leer sein!");
        }
        this.grafikkarte = grafikkarte;
        return this;
    }

    public RechnerBuilder setFestplatteSSD(String festplatteSSD) {
        if(isStringEmpty(festplatteSSD)) {
            throw new IllegalArgumentException("Festplatte SSD darf nicht leer sein!");
        }
        try {
            return setFestplatteSSD(Integer.parseInt(festplatteSSD));
        } catch(NumberFormatException nfe) {
            throw new IllegalArgumentException("Nur Ganzzahlen bei Festplatte SSD eintragen!");
        }
    }

    public RechnerBuilder setFestplatteSSD(int festplatteSSD) {
        if(festplatteSSD < 0) {
            throw new IllegalArgumentException("Festplattengröße SSD darf nicht negativ sein!");
        }
        this.festplatteSSD = festplatteSSD;
        return this;
    }

    public RechnerBuilder setFestplatteHDD(String festplatteHDD) {
        if(isStringEmpty(festplatteHDD)) {
            throw new IllegalArgumentException("Festplatte HDD darf nicht leer sein!");
        }
        try {
            return setFestplatteSSD(Integer.parseInt(festplatteHDD));
        } catch(NumberFormatException nfe) {
            throw new IllegalArgumentException("Nur Ganzzahlen bei Festplatte HDD eintragen!");
        }
    }

    public RechnerBuilder setFestplatteHDD(int festplatteHDD) {
        if(festplatteHDD < 0) {
            throw new IllegalArgumentException("Festplattengröße HDD darf nicht negativ sein!");
        }
        this.festplatteHDD = festplatteHDD;
        return this;
    }

    public RechnerBuilder setRaum(Raum raum) {
        if(DataManager.getInstance().findRaumByID(raum.getId()) != null) {
            this.raum = raum;
            return this;
        }
        throw new IllegalArgumentException("Raum " + raum.getId() + "ist nicht in der DB!");
    }

    public Rechner createRechner() {
        return new Rechner(seriennummer, modell, hersteller, status, herstellergarantie, lieferdatum, raum, cpu, arbeitsspeicher, betriebssystem, typ, grafikkarte, festplatteSSD, festplatteHDD);
    }
}