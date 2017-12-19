package models.Observer;

import java.util.List;

public interface Observed {

    default void registerObserver(Observer observer) {
        getObservers().add(observer);
    }

    default void removeObserver(Observer observer){
        getObservers().remove(observer);
    }

    List<Observer> getObservers();

    boolean canObserve(Observer observer);
}
