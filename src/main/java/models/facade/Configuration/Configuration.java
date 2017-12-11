package models.facade.Configuration;

import models.maze.MazeBuilder;
import models.mazeObjects.bomb.Bomb;
import models.mazeObjects.gift.ObjectsFactory;

import java.awt.*;
import java.util.HashSet;
import java.util.Properties;
import java.util.Random;

public  class Configuration {

    private Properties info;

    private int bombsNum;
    private int giftsNum;
    private int monstersNum;
    private String gameMode;
    private int difficulty;
    private HashSet listOfWallPositions;

    private Random rand= new Random();
    public Configuration(Properties info)
    {
        this.bombsNum= Integer.parseInt(info.getProperty("number_of_bombs"));
        this.giftsNum=Integer.parseInt(info.getProperty("number_of_gifts"));
        this.monstersNum=Integer.parseInt(info.getProperty("number_of_monsters"));
        this.gameMode=info.getProperty("game_mode");
        this.difficulty=Integer.parseInt(info.getProperty("difficulty"));
        this.listOfWallPositions= this.setPositionsSet(info.getProperty("tempWallsList"));
    }

    public void loadConfiguration(){
        MazeBuilder builder = new MazeBuilder();
        Object bomb;

        for (int i=0;i<bombsNum;i++)
        {
            int x=rand.nextInt(30);
            int y=rand.nextInt(30);
            Point bombPosition = new Point(x,y);
            int range=rand.nextInt(5)+1;

            if (!listOfWallPositions.contains(bombPosition))
                bomb =ObjectsFactory.produce(ObjectsFactory.BOMB,range,bombPosition);
        }
    }


    private HashSet setPositionsSet (String WallPositions)
    {
        HashSet<Point> setOfWallCells = new HashSet();
        String[] pointString = WallPositions.split(" ");
        for (String s : pointString) {
            s.replace("(", "");
            s.replace(")", "");

            int x = Integer.parseInt(s.split(",")[0]);
            int y = Integer.parseInt(s.split(",")[1]);

            Point wallCellPoint = new Point(x, y);

            setOfWallCells.add(wallCellPoint);
        }

        return setOfWallCells;
    }

}
