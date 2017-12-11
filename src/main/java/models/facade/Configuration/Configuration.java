package models.facade.Configuration;

import models.maze.MazeBuilder;

import java.util.Properties;

public  class Configuration {

    private Properties info;

    public Configuration(Properties info) {
        this.info = info;
    }


    public void loadConfiguration(){
        MazeBuilder builder = new MazeBuilder();

    }

}
