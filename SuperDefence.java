import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public abstract class SuperDefence extends JFrame implements Observable {
    protected List<Observer> observers;
    protected String name;
    protected Strength strength;
    protected int soldierCount;
    protected int fuelAmount;
    protected int ammoAmount;
    protected int oxygenLevel;
    protected int energyLevel;
    protected int positionLevel;

    public SuperDefence(String name) {
        this.name = name;
        this.strength = Strength.MEDIUM;
        this.observers = new ArrayList<>();
        setupGUI();
    }

    protected abstract void setupGUI();

    public String getName() {
        return name;
    }

    public int getSoldierCount() {
        return soldierCount;
    }

    public int getFuelAmount() {
        return fuelAmount;
    }

    public int getAmmoAmount() {
        return ammoAmount;
    }

    public int getPositionLevel() {
        return positionLevel;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public int getOxygenLevel() {
        return oxygenLevel;
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    protected void updateStrength(Strength newStrength) {
        this.strength = newStrength;
        notifyObservers(name + " strength updated to " + newStrength);
    }

    protected void sendMessage(String message) {
        notifyObservers(name + ": " + message);
    }

}
