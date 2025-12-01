package htl.steyr.scheresteinpapier.Model;

public class Gesture {

    /**
     * 0 = Schere
     * 1 = Stein
     * 2 = Papier
     * 3 = Brunnen
     * 4 = unset
     */
    private int gesture;


    public Gesture() {
        setGesture(4);
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




