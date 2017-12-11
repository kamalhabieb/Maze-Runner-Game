package models.facade.Configuration;

import models.maze.MazeBuilder;
import models.mazeObjects.bomb.Bomb;

import java.awt.*;
import java.util.Properties;
import java.util.Random;

public  class Configuration {

    private Properties info;
    private int bombsNum;
    private int giftsNum;
    private int monstersNum;
    private String gameMode;
    private int difficulty;

    private Random rand= new Random();
    public Configuration(Properties info)
    {
        this.bombsNum= Integer.parseInt(info.getProperty("number_of_bombs"));
        this.giftsNum=Integer.parseInt(info.getProperty("number_of_gifts"));
        this.monstersNum=Integer.parseInt(info.getProperty("number_of_monsters"));
        this.gameMode=info.getProperty("game_mode");
        this.difficulty=Integer.parseInt(info.getProperty("difficulty"));
    }

    public void loadConfiguration(){
        MazeBuilder builder = new MazeBuilder();

        for (int i=0;i<bombsNum;i++)
        {
            int x=rand.nextInt(30);
            int y=rand.nextInt(30);
            int range=rand.nextInt(5)+1;
            Bomb bomb = new Bomb(range,new Point(x,y));
        }
    }

}
