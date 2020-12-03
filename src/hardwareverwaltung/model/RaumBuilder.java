package hardwareverwaltung.model;

import static hardwareverwaltung.util.Utilities.isStringEmpty;

public class RaumBuilder {
    private String id;
    private String typ;
    private double groesse;

    public RaumBuilder setId(String id) {
        if(isStringEmpty(id)) throw new IllegalArgumentException("Typ darf nicht leer sein!");
        if(id.length() == 4) {
            if(id.charAt(0) >= 65 && id.charAt(0) <= 90) {
                if(id.charAt(1) >= 48 && id.charAt(1) <= 57) {
                    if(id.charAt(2) >= 48 && id.charAt(2) <= 57) {
                        if(id.charAt(3) >= 48 && id.charAt(3) <= 57) {
                            this.id = id;
                            return this;
                        }
                    }
                }
            }
        }
        throw new IllegalArgumentException("Raum ID im falschem Format!\nDie Raum-ID muss 4-stellig sein.\n" +
                "Beginnent mit einem Großbuchstaben &\ngefolgt von 3 Ziffern.");
    }

    public RaumBuilder setTyp(String typ) {
        if(isStringEmpty(typ)) throw new IllegalArgumentException("Typ darf nicht leer sein!");
        this.typ = typ;
        return this;
    }

    public RaumBuilder setGroesse(double groesse) {
        if(groesse <= 0.0) throw new IllegalArgumentException("Groesse muss groesser 0 sein!");
        this.groesse = groesse;
        return this;
    }

    public RaumBuilder setGroesse(String groesse) {
        if(isStringEmpty(groesse)) {
            throw new IllegalArgumentException("Groesse darf nicht leer sein!");
        } else {
            try {
                if(groesse.contains(",")) {
                    groesse = groesse.replace(",", ".");
                }
                double d = Double.parseDouble(groesse);
                return setGroesse(d);
            } catch(NumberFormatException e) {
                throw new IllegalArgumentException("Groesse darf nur Zahlen enthalten!");
            }
        }
    }

    public Raum createRaum() {
        return new Raum(id, typ, groesse);
    }
}