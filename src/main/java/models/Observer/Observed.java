package models.Observer;

import java.util.ArrayList;

public interface Observed {
    ArrayList<Observer> observers = new ArrayList<>();

    default void registerObserver(Observer observer) {
        observers.add(observer);
    }

    default void removeObserver(Observer observer){
        observers.remove(observer);
    }
}
