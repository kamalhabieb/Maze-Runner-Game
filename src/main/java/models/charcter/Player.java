package models.charcter;

import javafx.scene.image.Image;
import models.Observer.Observed;
import models.Observer.Observer;
import models.charcter.autonomous.Flame;
import models.charcter.autonomous.Moth;
import models.search.Path;
import models.search.Path2D;
import models.facade.ControlTower;
import models.facade.Score;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;


public class Player extends Person implements PlayerObserver, Observed, Flame {

    List<Observer> playerObservers;
    private Score score;

    public Player(final ControlTower controlTower) {
        super(controlTower);
        playerObservers = new ArrayList<>();
        setVelocity(1);
        score = new Score();
        this.registerObserver(score);
        super.lives = 1;
    }

    @Override
    public void setPosition(final double x, final double y) {
        if (controlTower.grantPermission(this, new Point2D.Double(x, y))) {
            super.setPosition(x, y);
        }
    }

    @Override
    public void setDestinationX(final int destinationX) {
        super.setDestinationX(destinationX);
        super.setPosition(destinationX, getPosition().getY());
    }

    @Override
    public void setDestinationY(final int destinationY) {
        super.setDestinationY(destinationY);
        super.setPosition(getPosition().getX(), destinationY);

    }

    @Override
    public Image getImage() {
        Image image = state.getImage();
        super.imageWidth = (int) image.getWidth();
        if (this.isAnimated()) super.setCoordinates();
        return image;
    }


    @Override
    public void notifyAboutHealth(int effect) {
        playerObservers.forEach(n -> ((PlayerObserver) n).notifyAboutHealth(effect));
    }

    @Override
    public void notifyAboutAmmo(int effect) {
        playerObservers.forEach(n -> ((PlayerObserver) n).notifyAboutAmmo(effect));

    }

    @Override
    public void notifyAboutLives(int effect) {
        playerObservers.forEach(n -> ((PlayerObserver) n).notifyAboutLives(effect));

    }

    @Override
    public boolean affectHealthBy(int effect) {
        notifyAboutHealth(effect);
        return super.affectHealthBy(effect);
    }

    @Override
    public boolean affectAmmo(int effect) {
        notifyAboutAmmo(effect);
        return super.affectAmmo(effect);
    }

    public int getScore() {
        return score.getScore();
    }

    @Override
    public List<Observer> getObservers() {
        return playerObservers;
    }

    @Override
    public boolean canObserve(final Observer observer) {
        return observer instanceof PlayerObserver;
    }

    @Override
    public void draw(final Moth moth) {
        Path path = controlTower.getPath( this.getPosition(), moth.getPosition());
        moth.setPathToFlame(path);
    }
    public int getLives(){return this.lives;}
}
