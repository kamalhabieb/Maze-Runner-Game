package models.charcter;

import javafx.scene.image.Image;
import models.Observer.Observed;
import models.Observer.Observer;
import models.facade.ControlTower;
import models.facade.Score;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;


public class Player extends Person implements PlayerObserver, Observed {

    List<Observer> playerObservers;
    private Score score;

    public Player(final ControlTower controlTower) {
        super(controlTower);
        playerObservers = new ArrayList<>();
        setVelocity(1);
        score = new Score();
        this.registerObserver(score);
    }

    @Override
    public void setPosition(final double x, final double y) {
        if (controlTower.grantPermission(this, new Point2D.Double(x, y))) {
            super.setPosition(x, y);
            destinationX = x;
            destinationY = y;
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
       // System.out.println("spriteViewChanged");
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
}
