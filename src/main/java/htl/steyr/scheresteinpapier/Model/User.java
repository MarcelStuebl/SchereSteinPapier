package htl.steyr.scheresteinpapier.Model;

import javafx.beans.property.*;

public class User {
    private final IntegerProperty id;
    private final StringProperty vorname;
    private final StringProperty nachname;

    public User() {
        this. id = new SimpleIntegerProperty();
        this.vorname = new SimpleStringProperty();
        this.nachname = new SimpleStringProperty();
    }

    public User(int id, String vorname, String nachname) {
        this.id = new SimpleIntegerProperty(id);
        this.vorname = new SimpleStringProperty(vorname);
        this.nachname = new SimpleStringProperty(nachname);
    }

    // ID
    public int getId() { return id.get(); }
    public void setId(int id) { this. id.set(id); }

    // Vorname
    public String getVorname() { return vorname.get(); }
    public void setVorname(String vorname) { this.vorname. set(vorname); }

    // Nachname
    public String getNachname() { return nachname.get(); }
    public void setNachname(String nachname) { this. nachname.set(nachname); }
}