package models.facade;

import models.Observer.Observed;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class BigBen implements Observed {
    private static final long DEFAULT_TIME_STEP = 500;
    private static BigBen ourInstance;
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

    private Thread clockThread = new Thread(clock);
    private Runnable clockRunner = new Runnable() {
        @Override
        public void run() {
            clock.run();
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
    }

    public void begin() {
        isTicking = true;
        clockThread.start();
    }

    public void stop() {
        isTicking = false;
    }


    private void notifyObservers() {
        observers.forEach(n -> ((ClockObserver) n).notifyNewTick());
    }

    public void setTimeStep(final long timeStep) {
        this.timeStep = timeStep;
    }
}
