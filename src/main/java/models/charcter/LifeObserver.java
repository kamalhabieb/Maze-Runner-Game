package models.charcter;

import models.Observer.Observer;

public interface LifeObserver extends Observer {
    void notifyFuneralOf(AliveObject wasAlive);

    void notifyResurrectionOf(AliveObject wasDead);
}
