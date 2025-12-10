package htl.steyr.scheresteinpapier.Model;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String username;
    private int highscore;

    public User() {
        this.id = 0;
        this.username = "";
        this.highscore = 0;
    }

    public User(String username) {
        this.username = username;
        this.highscore = 0;
    }

    public User(int id, String username, int highscore) {
        this.id = id;
        this.username = username;
        this.highscore = highscore;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String vorname) {
        this.username = vorname;
    }

    public int getHighscore() {
        return highscore;
    }
    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }
}




