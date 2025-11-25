package htl.steyr.scheresteinpapier;

import htl.steyr.scheresteinpapier.Model.Gesture;

import java.util.Random;

public class Player {
    Gesture gesture = new Gesture();

    public void setSelectedGesture(Gesture gesture) {
        this.gesture = gesture;
    }
    public void setSelectedGesture(int gesture) {
        this.gesture.setGesture(gesture);
    }

    public Gesture getSelectedGesture() {
        return this.gesture;
    }


    public void setRandomGesture() {
        Random rand = new Random();
        int randomGesture = rand.nextInt(3);
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
        }

    }




}




