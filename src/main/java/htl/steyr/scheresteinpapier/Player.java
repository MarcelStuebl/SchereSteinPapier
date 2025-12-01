package htl.steyr.scheresteinpapier;

import htl.steyr.scheresteinpapier.Model.Gesture;

import java.util.Random;

public class Player {
    Gesture gesture = new Gesture();

    public Gesture getSelectedGesture() {
        return this.gesture;
    }
    public void setSelectedGesture(int gesture) {
        this.gesture.setGesture(gesture);
    }


    /**
     * Sets random gesture.
     * 0 = Schere
     * 1 = Stein
     * 2 = Papier
     * 3 = Brunnen
     */
    public void setRandomGesture() {
        Random rand = new Random();
        int randomGesture = rand.nextInt(4);
        switch (randomGesture) {
            case 0:
                this.gesture.setGesture(0);
                break;
            case 1:
                this.gesture.setGesture(1);
                break;
            case 2:
                this.gesture.setGesture(2);
                break;
            case 3:
                this.gesture.setGesture(3);
                break;
        }

    }


}




