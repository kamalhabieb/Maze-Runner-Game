package models.facade;

import models.charcter.Player;
import models.engine.Engine;
import models.engine.EngineFactory;
import models.facade.Configuration.Configuration;
import models.maze.GameMaze;
import models.maze.Maze;

import java.io.IOException;
import java.util.Properties;

public class Facade implements ControlTower,Observer {
    private Maze mazeG ;
    private Engine gameEngine;
    private Player player;
    public final String EASY="/easy.configuration";
    public final String MEDIUM="/medium.configuration";
    public final String HARD="/easy.configuration";



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
       gameEngine = EngineFactory.getInstance(gameInfo.getProperty("game_mode"));
    }
    public void doAction(){

    }
}
