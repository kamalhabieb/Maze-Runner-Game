package models.facade;

import controllers.command.Command;
import controllers.command.Receiver;
import models.Observer.Observed;
import models.charcter.*;
import models.charcter.autonomous.Flame;
import models.charcter.autonomous.Moth;
import models.charcter.states.StateFactory;
import models.charcter.weapons.bullets.Bullet;
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
import models.search.Path;
import models.wall.Wall;
import views.Drawable;

import java.awt.geom.Point2D;
import java.util.Random;


import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

import static models.charcter.states.Directions.die;
import static models.charcter.states.Directions.movingEast;

public class Facade implements ControlTower, ClockObserver, LifeObserver {
    private Maze mazeG;
    private Engine gameEngine;
    public Player player;
    private ArrayList<Drawable> drawables;
    private ArrayList<Monster> monsters;
    private ArrayList bullets;
    public static final String EASY = "/configurations/easy.configuration";
    public static final String MEDIUM = "/configurations/medium.configuration";
    public static final String HARD = "/configurations/hard.configuration";
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
    private ArrayBlockingQueue events;
    public static int currentMazeLength;
    public static int currentMazeWidth;

    public Facade() {
        drawables = new ArrayList<>();
        drawObservers = new ArrayList<>();
        monsters = new ArrayList<>();
        clockTower = BigBen.getInstance(REFRESH_STEP);
        clockTower.registerObserver(this);
        metadata = new GameMetadata();
        bullets = new ArrayList<>();
        events = new ArrayBlockingQueue(1000);
    }

    @Override
    public void notifyNewTick() {
        executeEvents();
        player.update(gameEngine);
        monsters.stream().forEach(n -> n.update(gameEngine));
        for (Object bullet : bullets) {
            ((Bullet) bullet).update(gameEngine);
        }
        populateDrawables();
        updateMetadata();
        notifyDraw();
    }

    private void executeEvents() {
        while (!events.isEmpty()) {
            ((Runnable) events.poll()).run();
        }
    }

    private void updateMetadata() {
        metadata.setAmmo(player.getAmmo());
        metadata.setHealth(player.getHealth());
        metadata.setScore(player.getScore());
        metadata.setLives(player.getLives());
    }

    public void notifyDraw() {
        drawObservers.stream().forEach(n -> n.notifyDraw((ArrayList<Drawable>) drawables.clone()));
    }

    public void notifyLose() {
        player.setState(StateFactory.getState(die));
        drawObservers.stream().forEach(n -> n.notifyDrawGameOver(drawables));

    }

    private void notifyWin() {
        drawObservers.stream().forEach(n -> n.notifyDrawWin(drawables));
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
        observe((ArrayList<MazeObject>) mazeG.getWallsArray().stream().filter(n-> ((Wall)n).isBreakable()).collect(Collectors.toList()));
        clockTower.begin();
        notifyDrawStatic(mazeG.getWallsArray());
        this.currentMazeLength = mazeG.getHeight();
        this.currentMazeWidth = mazeG.getWidth();
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
        if (this.win(host, newPosition)) {
            notifyWin();
            return true;
        }
        int x = (int) newPosition.getX();
        int y = (int) newPosition.getY();
        try {

            String state = player.getState().getClass().getSimpleName();
            if (state.equalsIgnoreCase("moveeast")) {
                x = x + cellSize;
                if (y % cellSize >= cellSize - offset) {//Offset
                    y = y + (cellSize - y % cellSize);
                } else if (y % cellSize <= offset && y % cellSize > 0) {//Offset
                    y = y - y % cellSize;
                } else if (y % cellSize > offset && y % cellSize < cellSize - offset) {
                    y = y - y % cellSize;
                    newPosition.setLocation(x / cellSize, y / cellSize);
                    mazeObject_2 = mazeG.getMazeObjectAtRelativePosition(newPosition);
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
                    mazeObject_2 = mazeG.getMazeObjectAtRelativePosition(newPosition);
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
                    mazeObject_2 = mazeG.getMazeObjectAtRelativePosition(newPosition);
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
                    mazeObject_2 = mazeG.getMazeObjectAtRelativePosition(newPosition);
                    y = y + cellSize;
                }
            }

            newPosition.setLocation(x / cellSize, y / cellSize);
            mazeObject = mazeG.getMazeObjectAtRelativePosition(newPosition);


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
        if(mazeObject instanceof Space){
            return true;
        }
        return false;
    }


    private boolean win(Host host, Point2D newPosition) {
        if (host instanceof Player) {
            int endPointX = Integer.parseInt(gameInfo.getProperty(END_POINT_X));
            int endPointY = Integer.parseInt(gameInfo.getProperty(END_POINT_Y));
            if (endPointX == newPosition.getX() && endPointY == newPosition.getY()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void notifyFuneralOf(final AliveObject wasAlive) {
        if (wasAlive instanceof Player) {
            lose();
        }
        try {
            mazeG.RemoveMazeObjectWithRelativePosition((MazeObject) wasAlive, ((Matter) wasAlive).getPosition());
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
    }

    private void lose() {
        notifyLose();
    }

    @Override
    public void notifyResurrectionOf(final AliveObject wasDead) {
        if (wasDead instanceof Player) {
            player.setDestinationX((int) ((Player) wasDead).getPosition().getX());
            player.setDestinationY((int) ((Player) wasDead).getPosition().getY());
        }
    }

    @Override
    public Path getPath(final Point2D position, final Point2D position1) {
        return mazeG.getPath(position, position1);
    }

    @Override
    public void addBullet(BulletImpl bullet) {
        this.bullets.add(bullet);
    }

    @Override
    public boolean grantPermission(Visitor visitor, Point2D newPosition) {
        MazeObject mazeObject;
        int x = (int) newPosition.getX();
        int y = (int) newPosition.getY();
        try {
            mazeObject = mazeG.getMazeObjectAtAbsolutePosition(newPosition);
            if (mazeObject instanceof Space) {
                return true;
            }
            if (mazeObject instanceof Host) {
                visitor.visit(mazeObject);
                return false;
            }
        } catch (InvalidPositionException e) {
            return false;
        }

        return false;
    }

    @Override
    public void remove(BulletImpl bullet) {
        events.add((Runnable) () -> bullets.remove(bullet));
    }

    @Override
    public void drawToFlame(final Moth moth) {
        events.add((Runnable) () -> player.draw(moth));
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

    public GameMetadata getMetadata() {
        return metadata;
    }


}


