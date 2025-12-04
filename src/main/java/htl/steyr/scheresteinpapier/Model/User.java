package htl.steyr.scheresteinpapier.Model;

import javafx.beans.property.*;

public class User {
    private int id;
    private String vorname;
    private String nachname;
    private int highscore;

    public User() {
        this.id = 0;
        this.vorname = "";
        this.nachname = "";
        this.highscore = 0;
    }

    public User(int id, String vorname, String nachname, int highscore) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.highscore = highscore;
    }

    // ID
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    // Vorname
    public String getVorname() { return vorname; }
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    // Nachname
    public String getNachname() { return nachname; }
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }


    public int getHighscore() {
        return highscore;
    }
    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }
}