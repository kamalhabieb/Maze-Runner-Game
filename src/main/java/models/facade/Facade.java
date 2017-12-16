package models.facade;

import controllers.command.Command;
import controllers.command.Receiver;
import models.charcter.Monster;
import models.charcter.Player;
import models.engine.Engine;
import models.engine.EngineFactory;
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
import java.util.Properties;

public class Facade implements ControlTower, Observer {
    private Maze mazeG;
    private Engine gameEngine;
    private Player player;
    private ArrayList<Drawable> drawables;
    public static final String EASY = "/configurations/easy.configuration";
    public final String MEDIUM = "/configurations/medium.configuration";
    public final String HARD = "/configurations/easy.configuration";
    public final String GAME_MODE = "game_mode";
    public final String START_POINT_X = "start_X";
    public final String START_POINT_Y = "start_Y";
    public final String END_POINT_X = "end_X";
    public final String END_POINT_Y = "end_Y";
    private ArrayList<Monster> monsters;
    private ArrayList<DrawObserver> drawObservers;
    private BigBen clockTower;

    public Facade() {
        drawables = new ArrayList<>();
        drawObservers = new ArrayList<>();
        clockTower = BigBen.getInstance(17);
        clockTower.registerObserver(this);
    }

    @Override
    public void notifyNewTick() {
        player.update(gameEngine);
        //monsters.stream().forEach(n -> n.update(gameEngine));
        populateDrawables();
        player.getImage();
        notifyDraw();
    }

    public void notifyDraw() {
        drawObservers.stream().forEach(n -> n.notifyDraw(drawables));
    }

    public void populateDrawables() {
        drawables.clear();
        drawables.addAll(mazeG.getMazeObjectsArray());
        drawables.add(player);
        //drawables.addAll(monsters);
    }

    public void initializeGame(String mode) {
        Properties gameInfo = new Properties();
        try {
            gameInfo.load(getClass().getResourceAsStream(mode));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Configuration configuration = new Configuration(gameInfo);
        mazeG = configuration.loadConfiguration();
        gameEngine = EngineFactory.getInstance(gameInfo.getProperty(GAME_MODE));
        player = new Player(this);
        player.setPosition(Integer.parseInt(gameInfo.getProperty(START_POINT_X)), Integer.parseInt(gameInfo.getProperty(START_POINT_Y)));
        clockTower.begin();
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

    public void registerObserver(DrawObserver observer){
        drawObservers.add(observer);
    }
}
