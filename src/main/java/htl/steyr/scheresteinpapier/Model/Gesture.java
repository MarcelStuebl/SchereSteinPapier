package htl.steyr.scheresteinpapier.Model;

public class Gesture {

    private int gesture;
    /**
     * 0 = Schere
     * 1 = Stein
     * 2 = Papier
     * 3 = unset
     */

    public Gesture() {
        setGesture(3);
    }

    public Gesture(int gesture) {
        setGesture(gesture);
    }

    public int getID() {
        return gesture;
    }
    public void setGesture(int gesture) {
        this.gesture = gesture;
    }
    public int getGesture() {
        return gesture;
    }



}




