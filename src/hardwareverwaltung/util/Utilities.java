package hardwareverwaltung.util;

/**
 * Stellt nützliche Utils zu verfügung.
 */
public class Utilities {
    /**
     * Überprüft ob der übergebende String leer ist.
     * @param s der zu Überprüfende String.
     * @return true wenn der String leer ist oder nur Leerzeichen enthält.
     */
    public static boolean isStringEmpty(String s) {
        return s == null || s.equals("") || s.trim().equals("");
    }
}
