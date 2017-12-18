package models.facade;

import controllers.command.Command;
import controllers.command.Receiver;
import models.Observer.Observed;
import models.charcter.*;
import models.engine.Engine;
import models.engine.EngineFactory;
import models.engine.Matter;
import models.facade.Configuration.Configuration;
import models.maze.InvalidPositionException;
import models.maze.Maze;
import models.maze.MazeObject;
import models.mazeObjects.Host;
import models.mazeObjects.Visitor;
import models.wall.Wall;
import views.Drawable;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class Facade implements ControlTower, ClockObserver, LifeObserver {
    private Maze mazeG;
    private Engine gameEngine;
    public static Player player;
    private ArrayList<Drawable> drawables;
    private ArrayList<Monster> monsters;
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
    public final int REFRESH_STEP = 17;
    private ArrayList<DrawObserver> drawObservers;
    private BigBen clockTower;
    private Properties gameInfo;

    public Facade() {
        drawables = new ArrayList<>();
        drawObservers = new ArrayList<>();
        monsters = new ArrayList<>();
        clockTower = BigBen.getInstance(REFRESH_STEP);
        clockTower.registerObserver(this);
    }

    @Override
    public void notifyNewTick() {
        player.update(gameEngine);
        //monsters.stream().forEach(n -> n.update(gameEngine));
        populateDrawables();
        notifyDraw();
    }

    public void notifyDraw() {
        drawObservers.stream().forEach(n -> n.notifyDraw(drawables));
    }

    public void populateDrawables() {
        drawables.clear();
        drawables.addAll(filterWalls(mazeG.getMazeObjectsArray()));
        drawables.add(player);
        //drawables.addAll(monsters);
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
        observe(mazeG.getMazeObjectsArray());
        clockTower.begin();
        notifyDrawStatic(mazeG.getMazeObjectsArray());
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

    public void registerObserver(DrawObserver observer) {
        drawObservers.add(observer);
    }

    private void generateMonsters(int numberOfMonsters, String mode) {
        MonstersFactory monstersFactory = new MonstersFactory();
        for (int i = 0; i < numberOfMonsters; i++) {
            monsters.add(monstersFactory.GetMonster(mode + "monster", this));
        }
        //TODO randomize the monsters places
    }
}


