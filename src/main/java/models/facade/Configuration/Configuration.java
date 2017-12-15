package models.facade.Configuration;

import models.engine.Engine;
import models.engine.EngineFactory;
import models.maze.Maze;
import models.maze.MazeBuilder;
import models.maze.MazeObject;
import models.mazeObjects.ObjectsFactory;

import java.awt.*;
import java.util.HashSet;
import java.util.Properties;
import java.util.Random;

public  class Configuration {

    private int range_of_bomb_actions =5;
    private int range_of_gift_actions=5;
    private int mazeLength;
    private int mazeWidth;
    private int bombsNum;
    private int giftsNum;
    private int monstersNum;
    private String gameMode;
    private HashSet <String> listOfTakenPositions;
    private Random rand= new Random();

    public Configuration(Properties info)
    {
        this.mazeLength=30; //replace 30 with maze Length of the template
        this.mazeWidth=30; //replace 30 with maze Width of the template
        this.bombsNum= Integer.parseInt(info.getProperty("number_of_bombs"));
        this.giftsNum=Integer.parseInt(info.getProperty("number_of_gifts"));
        this.monstersNum=Integer.parseInt(info.getProperty("number_of_monsters"));
        this.gameMode=info.getProperty("game_mode");
        this.listOfTakenPositions= this.makeSetOFWallCellsPositions(info.getProperty("tempWallsList"));
    }

    /*initializations for new game*/
    public Maze loadConfiguration(){
        MazeBuilder builder = new MazeBuilder();

        /*Adding Bombs to MazeBuilder in Random valid positions*/
        for (int i=0;i<bombsNum;i++)
        {
            int x=rand.nextInt(mazeWidth);
            int y=rand.nextInt(mazeLength);
            Point bombPosition = new Point(x,y);
            String bombPositionString = bombPosition.toString();
            int range=rand.nextInt(range_of_bomb_actions)+1;// a random number from the interval [1,num_of_bomb_types]

            if ( !listOfTakenPositions.contains(bombPositionString) )
            {
                MazeObject bomb = ObjectsFactory.produce(ObjectsFactory.BOMB, range, bombPosition);
                builder.addMazeObject(bomb,bombPosition);
            }
            else i--;
        }

        /*Adding Gifts to MazeBuilder in Random valid positions*/
        for (int i=0;i<giftsNum;i++)
        {
            int x=rand.nextInt(mazeWidth);
            int y=rand.nextInt(mazeLength);
            Point giftPosition = new Point(x,y);
            String giftPositionString = giftPosition.toString();
            int range=rand.nextInt(range_of_gift_actions)+1;// a random number from the interval [1,num_of_gift_types]

            if ( !listOfTakenPositions.contains(giftPositionString) )
            {
                boolean healthGift = rand.nextBoolean();
                boolean  liveGift = rand.nextBoolean();
                if (healthGift)
                {
                    MazeObject gift = ObjectsFactory.produce(ObjectsFactory.HEAL_GIFT, range, giftPosition);
                    builder.addMazeObject(gift,giftPosition);
                }
                else if (liveGift)
                {
                    //waiting for implementation;
                }
                else
                {
                    MazeObject gift = ObjectsFactory.produce(ObjectsFactory.AMMO_GIFT, range, giftPosition);
                    builder.addMazeObject(gift,giftPosition);
                }
            }
            else i--;
        }
        /*Adding Ghosts to MazeBuilder in Random valid positions*/
        for (int i=0;i<monstersNum;i++)
        {
            int x=rand.nextInt(mazeWidth);
            int y=rand.nextInt(mazeLength);
            Point bombPosition = new Point(x,y);
            String bombPositionString = bombPosition.toString();
            int range=rand.nextInt(range_of_bomb_actions)+1;// a random number from the interval [1,num_of_bomb_types]

            if ( !listOfTakenPositions.contains(bombPositionString) )
            {
                //make an object from class monster
                //add monster to the make
            }

            else i--;
        }

        return builder.buildMaze();
    }


    /*
    * takes a String of points of Wall cells
    * and convert it to a hash set
    */
    private HashSet makeSetOFWallCellsPositions (String WallPositions)
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
