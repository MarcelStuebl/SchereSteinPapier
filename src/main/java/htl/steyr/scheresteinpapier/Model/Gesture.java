package htl.steyr.scheresteinpapier.Model;

public class Gesture {

    private int gesture;
    /**
     * 0 = Schere
     * 1 = Stein
     * 2 = Papier
     */


    public Gesture(int gesture) {
        setGesture(gesture);
    }

    public int getGesture() {
        return gesture;
    }
    public void setGesture(int gesture) {
        this.gesture = gesture;
    }




}




