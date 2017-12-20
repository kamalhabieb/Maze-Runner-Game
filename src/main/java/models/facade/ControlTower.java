package models.facade;

import models.Observer.Observer;
import models.charcter.AliveObject;
import models.charcter.weapons.bullets.BulletImpl;
import models.mazeObjects.Host;
import models.mazeObjects.Visitor;
import models.search.Path;

import java.awt.geom.Point2D;

public interface ControlTower extends Observer{
    boolean grantPermission(Host host, Point2D newPosition);

    void notifyFuneralOf(AliveObject wasAlive);

    void notifyResurrectionOf(AliveObject wasDead);

    Path getPath(Point2D position, Point2D position1);

    void addBullet(BulletImpl bullet);
    
    boolean grantPermission(Visitor visitor, Point2D newPosition);

    void remove(BulletImpl bullet);
}
