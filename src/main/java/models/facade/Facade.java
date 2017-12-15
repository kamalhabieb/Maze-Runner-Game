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
    public final String EASY = "/easy.configuration";
    public final String MEDIUM = "/medium.configuration";
    public final String HARD = "/easy.configuration";
    public final String GAME_MODE = "game_mode";
    public final String START_POINT_X = "start_X";
    public final String START_POINT_Y = "start_Y";
    private ArrayList<Monster> monsters;
    private ArrayList<DrawObserver> drawObservers;

    public Facade() {
        drawables = new ArrayList<>();
        drawObservers = new ArrayList<>();
    }

    @Override
    public void notifyNewTick() {
        player.update(gameEngine);
        monsters.stream().forEach(n -> n.update(gameEngine));
        populateDrawables();
        notifyDraw();
    }

    private void notifyDraw() {
        drawObservers.stream().forEach(n->n.notifyDraw(drawables));
    }

    private void populateDrawables() {
        drawables.clear();
        drawables.addAll(mazeG.getMazeObjectsArray());
        drawables.add(player);
        drawables.addAll(monsters);
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

}
