package models.facade.Configuration;

import models.maze.MazeBuilder;
import models.maze.MazeObject;
import models.mazeObjects.ObjectsFactory;

import java.awt.*;
import java.util.HashSet;
import java.util.Properties;
import java.util.Random;

public  class Configuration {

    private int bombsNum;
    private int giftsNum;
    private int monstersNum;
    private String gameMode;
    private int difficulty;
    private HashSet <String> listOfWallPositions;
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


        for (int i=0;i<bombsNum;i++)
        {
            int x=rand.nextInt(30);//replace 30 with maze Width of the template
            int y=rand.nextInt(30);//replace 30 with maze Length of the template
            Point bombPosition = new Point(x,y);
            String bombPositionString = bombPosition.toString();
            int range=rand.nextInt(5)+1;

            if ( !listOfWallPositions.contains(bombPositionString) )
            {
                MazeObject bomb = ObjectsFactory.produce(ObjectsFactory.BOMB, range, bombPosition);
                builder.addMazeObject(bomb,bombPosition);
            }
        }
    }


    private HashSet setPositionsSet (String WallPositions)
    {
        HashSet<String> setOfWallCells = new HashSet();
        String[] pointString = WallPositions.split(" ");
        for (String s : pointString) {
            s=s.replace("(", "");
            s=s.replace(")", "");

            int x = Integer.parseInt(s.split(",")[0]);
            int y = Integer.parseInt(s.split(",")[1]);

            Point wallCellPoint = new Point(x, y);
            String wallCellPointString = wallCellPoint.toString();
            setOfWallCells.add(wallCellPointString);
        }

        return setOfWallCells;
    }

}
