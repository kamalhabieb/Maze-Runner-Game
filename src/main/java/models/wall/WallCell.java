package models.wall;

import javafx.scene.image.Image;
import models.Observer.Observed;
import models.Observer.Observer;
import models.charcter.AliveObject;
import models.charcter.LifeObserver;
import models.mazeObjects.Host;
import models.mazeObjects.Visitor;
import models.mazeObjects.gift.Gift;
import views.Drawable;
import views.flyweight.WallImage;

import java.util.ArrayList;
import java.util.List;

public class WallCell extends Drawable implements Wall, Host, AliveObject, Observed {

    private boolean breakable;
    private int MAX_HEALTH = 50;
    private int HALF_HEALTH = 25;
    private int MIN_HEALTH = 0;
    private int health;
    private List<Observer> observers;

    public WallCell() {
        health = MAX_HEALTH;
        observers = new ArrayList<>();
    }


    @Override
    public void setBreakable(boolean breakable) {
        if (breakable)
            this.breakable = true;
        else this.breakable = false;
    }

    @Override
    public boolean isBreakable() {
        if (breakable)
            return true;
        return false;

    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public boolean affectHealthBy(int effect) {
        if (breakable && health > MIN_HEALTH) {
            health += effect;
            if (health<=MIN_HEALTH)
            {
                destroy();
            }

            return true;
        }
        return false;
    }

    @Override
    public void destroy() {
        /*final URL resource = getClass().getResource("/music/giftSound.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();*/
        //todo el sound aho, uncomment
        getObservers().forEach(n -> ((LifeObserver) n).notifyFuneralOf(WallCell.this));
    }

    @Override
    public void revive() {

    }

    @Override
    public Image getImage() {

        if (this.isBreakable()) {
            if (this.health <= HALF_HEALTH) {
                return WallImage.getImage(WallImage.breakingState1);
            }

            return WallImage.getImage(WallImage.breakable);
        }
        return WallImage.getImage(WallImage.unBreakable);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }


    @Override
    public List<Observer> getObservers() {
        return observers;
    }

    @Override
    public boolean canObserve(Observer observer) {
        return observer instanceof LifeObserver;
    }
}