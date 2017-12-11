package models.facade;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class BigBen {
    private static final long DEFAULT_TIME_STEP = 500;
    private static BigBen ourInstance;
    private ArrayList<Observer> observers;
    private boolean isTicking;
    private long timeStep;

    private Runnable clock = new Runnable() {
        @Override
        public void run() {
            while (isTicking) {
                try {
                    sleep(timeStep);
                    notifyObservers();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    public static BigBen getInstance() {
        if (ourInstance == null) ourInstance = new BigBen(DEFAULT_TIME_STEP);
        return ourInstance;
    }

    public static BigBen getInstance(long timeStep) {
        if (ourInstance == null) ourInstance = new BigBen(timeStep);
        else ourInstance.setTimeStep(timeStep);
        return ourInstance;
    }

    private BigBen(final long timeStep) {
        this.timeStep = timeStep;
        observers = new ArrayList<>();
    }

    public void begin() {
        isTicking = true;
        clock.run();
    }

    public void stop() {
        isTicking = false;
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.notifyNewTick();
        }
    }

    public void setTimeStep(final long timeStep) {
        this.timeStep = timeStep;
    }
}
