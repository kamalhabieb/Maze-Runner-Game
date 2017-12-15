package models.facade;

import models.facade.Configuration.Configuration;
import models.maze.GameMaze;
import models.maze.InvalidPositionException;
import models.maze.Maze;
import models.maze.MazeObject;
import models.mazeObjects.Host;
import models.mazeObjects.Visitor;
import models.mazeObjects.space.Space;
import models.wall.Wall;

import java.awt.*;
import java.io.IOException;
import java.util.Properties;

public class Facade implements ControlTower, Observer {
    private Maze mazeG;
    public final String EASY = "/easy.configuration";
    public final String MEDIUM = "/medium.configuration";
    public final String HARD = "/easy.configuration";


    @Override
    public void notifyNewTick() {
    }

    public void initializeGame(String mode) {
        Properties gameInfo = new Properties();
        try {
            gameInfo.load(getClass().getResourceAsStream(mode));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Configuration configuration = new Configuration(gameInfo);
        mazeG = configuration.loadConfiguration();
    }

    @Override
    public boolean grantPermission(final Host host, final Point newPosition) {
        MazeObject mazeObject;
        try {
            mazeObject = mazeG.getMazeObjectAtAbsolutePosition(newPosition);
        } catch (InvalidPositionException e) {
            return false;
        }
        if (mazeObject instanceof Wall) {
            return false;
        }
        if (mazeObject instanceof Visitor) {
            host.accept((Visitor) mazeObject);
            return true;
        }
        return true;
    }
}
