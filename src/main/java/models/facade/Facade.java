package models.facade;

import controllers.command.Command;
import controllers.command.Receiver;
import models.Observer.Observed;
import models.charcter.*;
import models.charcter.autonomous.Moth;
import models.charcter.weapons.bullets.BulletImpl;
import models.charcter.monsters.Monster;
import models.charcter.monsters.MonstersFactory;
import models.engine.Engine;
import models.engine.EngineFactory;
import models.engine.Matter;
import models.facade.Configuration.Configuration;
import models.maze.InvalidPositionException;
import models.maze.Maze;
import models.maze.MazeObject;
import models.mazeObjects.Host;
import models.mazeObjects.Visitor;
import models.mazeObjects.space.Space;
import models.search.Graph;
import models.search.MatrixGraph;
import models.search.Path;
import models.wall.Wall;
import views.Drawable;

import java.awt.geom.Point2D;
import java.util.Random;


import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Facade implements ControlTower, ClockObserver, LifeObserver {
    private Maze mazeG;
    private Engine gameEngine;
    public Player player;
    private ArrayList<Drawable> drawables;
    private ArrayList<Monster> monsters;
    private ArrayList bullets;
    public static final String EASY = "/configurations/easy.configuration";
    public final String MEDIUM = "/configurations/medium.configuration";
    public final String HARD = "/configurations/easy.configuration";
    public final String GAME_DIFFICULTY = "difficulty";
    public final String GAME_MODE = "game_mode";
    public final String MONSTERS_NUMBER = "number_of_monsters";
    public final String START_POINT_X = "start_X";
    public final String START_POINT_Y = "start_Y";
    public final String END_POINT_X = "end_X";
    public final String END_POINT_Y = "end_Y";
    public final int REFRESH_STEP = 10;
    private final int cellSize = 30;
    private final int offset = 3;
    private final int vertical_offset = 3;
    private ArrayList<DrawObserver> drawObservers;
    private BigBen clockTower;
    private Properties gameInfo;
    private GameMetadata metadata;

    public Facade() {
        drawables = new ArrayList<>();
        drawObservers = new ArrayList<>();
        monsters = new ArrayList<>();
        clockTower = BigBen.getInstance(REFRESH_STEP);
        clockTower.registerObserver(this);
        metadata = new GameMetadata();
        bullets = new ArrayList<>();
    }

    @Override
    public void notifyNewTick() {
        player.update(gameEngine);
        monsters.stream().forEach(n -> n.update(gameEngine));
        populateDrawables();
        updateMetadata();
        notifyDraw();
    }

    private void updateMetadata() {
        metadata.setAmmo(player.getAmmo());
        metadata.setHealth(player.getHealth());
        metadata.setScore(player.getScore());
        metadata.setLives(player.getLives());
    }

    public void notifyDraw() {
        drawObservers.stream().forEach(n -> n.notifyDraw(drawables));
    }

    public void populateDrawables() {
        drawables.clear();
        // drawables.addAll(filterWalls(mazeG.getMazeObjectsArray()));
        drawables.addAll(mazeG.getBombsGiftsArray());
        drawables.addAll(bullets);
        drawables.add(player);
        drawables.addAll(monsters);
    }

    private Collection<? extends Drawable> filterWalls(final ArrayList<Drawable> mazeObjectsArray) {
        return mazeObjectsArray.stream().filter(n -> !(n instanceof Wall)).collect(Collectors.toList());
    }

    public void initializeGame(String mode) {
        gameInfo = new Properties();
        try {
            gameInfo.load(getClass().getResourceAsStream(mode));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Configuration configuration = new Configuration(gameInfo);
        mazeG = configuration.loadConfiguration();
        gameEngine = EngineFactory.getInstance(gameInfo.getProperty(GAME_MODE));
        player = new Player(this);
        player.setSrcX(0);
        player.setSrcY(0);
        player.setSrcWidth(33);
        player.setSrcHeight(50);
        player.setAnimated(true, 33);
        player.setDestinationWidth(Integer.parseInt((String) gameInfo.get("cell_width")));
        player.setDestinationHeight(Integer.parseInt((String) gameInfo.get("cell_width")));
        player.setDestinationX(Integer.parseInt(gameInfo.getProperty(START_POINT_X)) * Integer.parseInt((String) gameInfo.get("cell_width")));
        player.setDestinationY(Integer.parseInt(gameInfo.getProperty(START_POINT_Y)) * Integer.parseInt((String) gameInfo.get("cell_width")));
        this.generateMonsters(Integer.parseInt(gameInfo.getProperty(MONSTERS_NUMBER)), gameInfo.getProperty(GAME_DIFFICULTY));
        this.monstersPositions(configuration.getListOfTakenPositions());
        monsters.forEach(n -> player.draw((Moth) n));
        observe(mazeG.getBombsGiftsArray());
        clockTower.begin();
        notifyDrawStatic(mazeG.getWallsArray());
    }

    private void observe(final ArrayList<MazeObject> mazeObjectsArray) {
        mazeObjectsArray.forEach(n -> {
            if (n instanceof Observed) {
                ((Observed) n).registerObserver((models.Observer.Observer) Facade.this);
            }
        });
    }

    private void notifyDrawStatic(final ArrayList<Drawable> mazeObjectsArray) {
        drawObservers.stream().forEach(n ->
                n.notifyDrawStatic(mazeObjectsArray.stream()
                        .filter(x -> x instanceof Wall)
                        .collect(Collectors.toList())));
    }

    public void excute(Command command) {
        command.execute((Receiver) player);
    }

    @Override
    public boolean grantPermission(final Host host, final Point2D newPosition) {
        MazeObject mazeObject;
        MazeObject mazeObject_2 = new Space();
        int x = (int) newPosition.getX();
        int y = (int) newPosition.getY();
        try {
            // Changes TO Avoid Hitting a Wall
           /* System.out.print(x + " ");
            System.out.println(y);
*/
            String state = player.getState().getClass().getSimpleName();
            //System.out.println(state);
            if (state.equalsIgnoreCase("moveeast")) {
                x = x + cellSize;
                if (y % cellSize >= cellSize - offset) {//Offset
                    y = y + (cellSize - y % cellSize);
                } else if (y % cellSize <= offset && y % cellSize > 0) {//Offset
                    y = y - y % cellSize;
                } else if (y % cellSize > offset && y % cellSize < cellSize - offset) {
                    y = y - y % cellSize;
                    newPosition.setLocation(x / cellSize, y / cellSize);
                    mazeObject_2 = mazeG.getMazeObjectAtAbsolutePosition(newPosition);
                    y = y + cellSize;

                }
            } else if (state.equalsIgnoreCase("movesouth")) {
                y = y + cellSize;
                if (x % cellSize >= cellSize - vertical_offset) {//Offset
                    x = x + (cellSize - x % cellSize);
                } else if (x % cellSize <= vertical_offset && x % cellSize > 0) {//Offset
                    x = x - x % cellSize;
                } else if (x % cellSize > vertical_offset && x % cellSize < cellSize - vertical_offset) {
                    x = x - x % cellSize;
                    newPosition.setLocation(x / cellSize, y / cellSize);
                    mazeObject_2 = mazeG.getMazeObjectAtAbsolutePosition(newPosition);
                    x = x + cellSize;
                }
            } else if (state.equalsIgnoreCase("movenorth")) {
                if (x % cellSize >= cellSize - vertical_offset) {//Offset
                    x = x + (cellSize - x % cellSize);
                } else if (x % cellSize <= vertical_offset && x % cellSize > 0) {//Offset
                    x = x - x % cellSize;
                } else if (x % cellSize > vertical_offset && x % cellSize < cellSize - vertical_offset) {
                    x = x - x % cellSize;
                    newPosition.setLocation(x / cellSize, y / cellSize);
                    mazeObject_2 = mazeG.getMazeObjectAtAbsolutePosition(newPosition);
                    x = x + cellSize;
                }
            } else if (state.equalsIgnoreCase("movewest")) {
                if (y % cellSize >= cellSize - offset) {//Offset
                    y = y + (cellSize - y % cellSize);
                } else if (y % cellSize <= offset && y % cellSize > 0) {//Offset
                    y = y - y % cellSize;
                } else if (y % cellSize > offset && y % cellSize < cellSize - offset) {
                    y = y - y % cellSize;

                    newPosition.setLocation(x / cellSize, y / cellSize);
                    mazeObject_2 = mazeG.getMazeObjectAtAbsolutePosition(newPosition);
                    y = y + cellSize;
                }
            }
          /*  System.out.print("1 ==>" +x + " ");
            System.out.println(y);
*/           /* x = (int) (newPosition.getX()/cellSize);
            y = (int) (newPosition.getY()/cellSize);*/
            newPosition.setLocation(x / cellSize, y / cellSize);
            mazeObject = mazeG.getMazeObjectAtAbsolutePosition(newPosition);

           /* System.out.print("2 ==>" +x + " ");
            System.out.println(y);*/
//            System.out.println(mazeObject);

        } catch (InvalidPositionException e) {
            return false;
        }
        if (mazeObject instanceof Wall || mazeObject_2 instanceof Wall) {
            return false;
        }
        if (mazeObject instanceof Visitor) {
            host.accept((Visitor) mazeObject);
            return true;
        }
        return true;
    }

    @Override
    public void notifyFuneralOf(final AliveObject wasAlive) {
        if (wasAlive == player) {
            lose();
        }
        try {
            mazeG.RemoveMazeObjectWithRelativePosition((MazeObject) wasAlive, ((Matter) wasAlive).getPosition());
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
    }

    private void lose() {
        //TODO implement lose scenario
    }

    @Override
    public void notifyResurrectionOf(final AliveObject wasDead) {
        player.setDestinationX(Integer.parseInt(gameInfo.getProperty(START_POINT_X)) * Integer.parseInt((String) gameInfo.get("cell_width")));
        player.setDestinationY(Integer.parseInt(gameInfo.getProperty(START_POINT_Y)) * Integer.parseInt((String) gameInfo.get("cell_width")));
    }

    @Override
    public Path getPath(final Point2D position, final Point2D position1) {
        return mazeG.getPath(position, position1);
    }


    public void registerObserver(DrawObserver observer) {
        drawObservers.add(observer);
    }

    private void generateMonsters(int numberOfMonsters, String mode) {
        MonstersFactory monstersFactory = new MonstersFactory();
        for (int i = 0; i < numberOfMonsters; i++) {
            monsters.add(monstersFactory.GetMonster(mode + "monster", this));
        }
    }

    public void monstersPositions(LinkedList<Point> listOfTakenPositions) {
        Random rand = new Random();
        for (int i = 0; i < monsters.size(); i++) {
            int x = rand.nextInt(mazeG.getWidth());
            int y = rand.nextInt(mazeG.getHeight());
            Point ghostPosition = new Point(x, y);
            if (!listOfTakenPositions.contains(ghostPosition)) {
                listOfTakenPositions.add(ghostPosition);
                monsters.get(i).setDestinationX(x * Integer.parseInt((String) gameInfo.get("cell_width")));
                monsters.get(i).setDestinationY(y * Integer.parseInt((String) gameInfo.get("cell_width")));
            } else i--;
        }
    }

    public void shutdown() {
        clockTower.stop();
    }

    public void fireWeapon() {
        BulletImpl bullet = player.fireWeapon();
        bullet.setSrcX(0);
        bullet.setSrcY(0);
        bullet.setSrcWidth(40);
        bullet.setSrcHeight(40);
        bullet.setDestinationX((int) (bullet.getPosition().getX() * cellSize));
        bullet.setDestinationY((int) (bullet.getPosition().getY() * cellSize));
        bullet.setDestinationWidth(cellSize);

    }

    public GameMetadata getMetadata() {
        return metadata;
    }
}


