package hardwareverwaltung.model;

import java.time.LocalDate;

public class Rechner extends Hardware {
    private String cpu;
    private int arbeitsspeicher;
    private String betriebssystem;
    private String typ;
    private String grafikkarte;
    private int festplatteSSD;
    private int festplatteHDD;

    public Rechner() {
        super();
    }

    @Override
    public String toStringExtraParams() {
        return ""
                + getCpu() + ";"
                + getArbeitsspeicher() + ";"
                + getBetriebssystem() + ";"
                + getTyp() + ";"
                + getGrafikkarte() + ";"
                + getFestplatteSSD() + ";"
                + getFestplatteHDD()
                ;
    }

    public Rechner(String seriennummer,
                   String modell,
                   String hersteller,
                   String status,
                   int herstellergarantie,
                   LocalDate lieferdatum,
                   Raum raum,
                   String cpu,
                   int arbeitsspeicher,
                   String betriebssystem,
                   String typ,
                   String grafikkarte,
                   int festplatteSSD,
                   int festplatteHDD) {
        super(
                seriennummer,
                modell,
                hersteller,
                status,
                herstellergarantie,
                lieferdatum,
                raum
        );
        this.cpu = cpu;
        this.arbeitsspeicher = arbeitsspeicher;
        this.betriebssystem = betriebssystem;
        this.typ = typ;
        this.grafikkarte = grafikkarte;
        this.festplatteSSD = festplatteSSD;
        this.festplatteHDD = festplatteHDD;
    }

    public String getCpu() {
        return cpu;
    }

    public int getArbeitsspeicher() {
        return arbeitsspeicher;
    }

    public String getBetriebssystem() {
        return betriebssystem;
    }

    public String getTyp() {
        return typ;
    }

    public String getGrafikkarte() {
        return grafikkarte;
    }

    public int getFestplatteSSD() {
        return festplatteSSD;
    }

    public int getFestplatteHDD() {
        return festplatteHDD;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public void setArbeitsspeicher(int arbeitsspeicher) {
        this.arbeitsspeicher = arbeitsspeicher;
    }

    public void setBetriebssystem(String betriebssystem) {
        this.betriebssystem = betriebssystem;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public void setGrafikkarte(String grafikkarte) {
        this.grafikkarte = grafikkarte;
    }

    public void setFestplatteSSD(int festplatteSSD) {
        this.festplatteSSD = festplatteSSD;
    }

    public void setFestplatteHDD(int festplatteHDD) {
        this.festplatteHDD = festplatteHDD;
    }
}
