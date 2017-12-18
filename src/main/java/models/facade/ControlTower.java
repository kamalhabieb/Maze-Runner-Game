package models.facade;

import javafx.geometry.Point2D;
import models.Observer.Observer;
import models.charcter.AliveObject;
import models.charcter.Person;
import models.mazeObjects.Host;

import java.awt.*;

public interface ControlTower extends Observer{
    boolean grantPermission(Host host, java.awt.geom.Point2D newPosition);

    void notifyFuneralOf(AliveObject wasAlive);

    void notifyResurrectionOf(AliveObject wasDead);
}
