package models.facade;

import models.Observer.Observer;

public interface ClockObserver extends Observer {
    void notifyNewTick();
}
