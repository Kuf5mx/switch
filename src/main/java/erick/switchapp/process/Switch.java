package erick.switchapp.process;

public class Switch {

    private boolean isOn;

    public void prender() {
        isOn = true;
    }

    public void apagar() {
        isOn = false;
    }

    public boolean isOn() {
        return isOn;
    }
}