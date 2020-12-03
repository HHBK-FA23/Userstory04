package hardwareverwaltung.logic;

import hardwareverwaltung.model.Drucker;
import hardwareverwaltung.model.Hardware;
import hardwareverwaltung.model.Raum;
import hardwareverwaltung.model.Rechner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static hardwareverwaltung.util.Utilities.isStringEmpty;

public class DataManager {
    private static DataManager dataManager = null;

    private final ObservableList<Hardware> druckerList;
    private final ObservableList<Hardware> rechnerList;
    private final ObservableList<Raum> raumList;

    private DataManager() {
        druckerList = FXCollections.observableArrayList();
        rechnerList = FXCollections.observableArrayList();
        raumList = FXCollections.observableArrayList();
    }

    /**
     * Liefert die aktuelle Version des DataManagers
     * @return aktuelle Version des DataManagers
     */
    public static DataManager getInstance() {
        if(DataManager.dataManager == null) {
            DataManager.dataManager = new DataManager();
        }
        return DataManager.dataManager;
    }

    /**
     * TODO doc
     * @param id
     * @return
     */
    public Hardware findHardwareByID(int id) {
        for(Hardware hw : rechnerList) {
            if(hw.getId() == id) {
                return hw;
            }
        }
        for(Hardware hw: druckerList) {
            if(hw.getId() == id) {
                return hw;
            }
        }
        return null;
    }

    public Hardware findHardwareByID(String id) {
        if(isStringEmpty(id)) throw new IllegalArgumentException("ID is empty");
        try {
            return findHardwareByID(Integer.parseInt(id));
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("ID is not a number");
        }
    }

    /**
     * Fügt das übergebene Hardware Objekt der Ensprechenden Liste hinzu.
     * @param hw das Hardware Objekt, welches der Liste hinzugefügt werden soll.
     */
    public void add(Hardware hw) {
        if(hw instanceof Drucker) {
            druckerList.add(hw);
        } else if(hw instanceof Rechner){
            rechnerList.add(hw);
        } else {
            throw new IllegalArgumentException("Keine kompatible Liste gefunden!");
        }

        for(Raum r : raumList) {
            if(r.getId() == hw.getRaum().getId()) {
                r.getHardwareList().add(hw);
            }
        }


    }

    /**
     * TODO doc
     * @param id
     * @return
     */
    public Raum findRaumByID(String id) {
        for(Raum r : raumList) {
            if(r.getId().equals(id)) {
                return r;
            }
        }
        return null;
    }

    /**
     * TODO doc
     * @param r
     */
    public void add(Raum r) {
        if (findRaumByID(r.getId()) == null) {
            raumList.add(r);
        } else throw new IllegalArgumentException("Raum mit ID " + r.getId() + " existiert schon!");
    }

    /**
     * Fügt alle übergebenden Hardware Objekte der Entsprechendem Liste hinzu.
     * @param hw die Hardware Objekte, welche den Listen hinzugefügt werden sollen.
     */
    public void addAll(Hardware... hw) {
        for (Hardware h : hw) {
            this.add(h);
        }
    }

    /**
     * TODO doc
     * @param rs
     */
    public void addAll(Raum... rs) {
        for(Raum r: rs) {
            this.add(r);
        }
    }

    /**
     * Liefert die Liste mit allen hinzugefügten Druckern
     * @return Liste mit allen hinzugefügten Druckern
     */
    public ObservableList<Hardware> getDruckerList() {
        return druckerList;
    }

    /**
     * Liefert die Liste mit allen hinzugefügten Rechnern
     * @return Liste mit allen hinzugefügten Rechnern
     */
    public ObservableList<Hardware> getRechnerList() {
        return rechnerList;
    }

    /**
     * TODO desc
     * @return
     */
    public ObservableList<Raum> getRaumList() {
        return raumList;
    }
}
