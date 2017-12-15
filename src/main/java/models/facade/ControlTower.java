package models.facade;

import models.mazeObjects.Host;

import java.awt.*;

public interface ControlTower {
    boolean grantPermission(Host host, Point newPosition);

}
