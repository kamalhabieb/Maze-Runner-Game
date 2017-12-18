package models.charcter;

import javafx.scene.image.Image;
import models.facade.ControlTower;

import java.awt.Point;
import java.util.ArrayList;


public class Player extends Person implements PlayerObserver {

    ArrayList<PlayerObserver> playerObservers;

    public Player(final ControlTower controlTower) {
        super(controlTower);
        playerObservers = new ArrayList<>();
        setVelocity(1);
    }

    @Override
    public void setPosition(final int x, final int y) {
        if (controlTower.grantPermission(this, new Point(x, y))) {
            super.setPosition(x, y);
            destinationX = x;
            destinationY = y;
        }
    }

    @Override
    public void setDestinationX(final int destinationX) {
        super.setDestinationX(destinationX);
        super.setPosition(destinationX, getPosition().y);
    }

    @Override
    public void setDestinationY(final int destinationY) {
        super.setDestinationY(destinationY);
        super.setPosition(getPosition().x, destinationY);

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
        for (PlayerObserver observer : playerObservers) {
            observer.notifyAboutHealth(effect);
        }
    }

    @Override
    public void notifyAboutAmmo(int effect) {
        for (PlayerObserver observer : playerObservers) {
            observer.notifyAboutAmmo(effect);
        }
    }

    @Override
    public void notifyAboutLives(int effect) {
        for (PlayerObserver observer : playerObservers) {
            observer.notifyAboutLives(effect);
        }
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
}
