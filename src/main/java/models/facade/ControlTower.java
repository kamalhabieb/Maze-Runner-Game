package models.facade;

import models.Observer.Observer;
import models.charcter.AliveObject;
import models.charcter.Person;
import models.mazeObjects.Host;

import java.awt.*;

public interface ControlTower extends Observer{
    boolean grantPermission(Host host, Point newPosition);

    void notifyFuneralOf(AliveObject wasAlive);

    void notifyResurrectionOf(AliveObject wasDead);
}
