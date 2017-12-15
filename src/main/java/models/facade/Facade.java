package models.facade;

import models.facade.Configuration.Configuration;
import models.maze.GameMaze;
import models.maze.Maze;

import java.io.IOException;
import java.util.Properties;

public class Facade implements ControlTower,Observer {
    private Maze mazeG ;
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
    }
}
