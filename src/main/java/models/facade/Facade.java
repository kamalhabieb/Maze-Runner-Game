package models.facade;

import models.charcter.Player;
import models.engine.Engine;
import models.engine.EngineFactory;
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

public class Facade implements ControlTower,Observer {
    private Maze mazeG ;
    private Engine gameEngine;
    private Player player;
    public final String EASY="/easy.configuration";
    public final String MEDIUM="/medium.configuration";
    public final String HARD="/easy.configuration";
    public final String GAME_MODE="game_mode";
    public final String START_POINT_X="start_X";
    public final String START_POINT_Y="start_Y";



    @Override
    public void notifyNewTick() {
    }

    public void initializeGame(String mode){
        Properties gameInfo = new Properties();
        try {
            gameInfo.load(getClass().getResourceAsStream(mode));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Configuration configuration = new Configuration(gameInfo);
        mazeG = configuration.loadConfiguration();
       gameEngine = EngineFactory.getInstance(gameInfo.getProperty(GAME_MODE));
       player = new Player(this);
       player.setPosition(Integer.parseInt(gameInfo.getProperty(START_POINT_X)), Integer.parseInt(gameInfo.getProperty(START_POINT_Y)));
    }
    public void doAction(){

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
