package hardwareverwaltung.model;

import java.util.ArrayList;
import java.util.List;

public class Raum {
    private String id;
    private String typ;
    private double groesse;
    private List<Hardware> hardwareList;

    protected Raum(String id, String typ, double groesse) {
        this();
        this.id = id;
        this.typ = typ;
        this.groesse = groesse;

    }

    protected Raum() {
        hardwareList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return id + "; " + typ + "; " + groesse;
    }

    public String getId() {
        return id;
    }

    public String getTyp() {
        return typ;
    }

    public double getGroesse() {
        return groesse;
    }

    public List<Hardware> getHardwareList() {
        return hardwareList;
    }
}
