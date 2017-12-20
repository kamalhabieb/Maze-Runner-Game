package models.mazeObjects.gift;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import models.Observer.Observed;
import models.Observer.Observer;
import models.charcter.AliveObject;
import models.charcter.LifeObserver;
import models.engine.Matter;
import models.mazeObjects.Visitor;
import views.Drawable;
import views.flyweight.GiftImage;

import java.awt.geom.Point2D;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public abstract class Gift extends Drawable implements Gift_I, AliveObject,
        Matter, Visitor, Observed {
    private Point2D pos;
    private boolean isCovered = true;
    private List<Observer> observers;

    public Gift() {
        observers = new ArrayList<>();
    }

    @Override
    public void accept(Visitor visitor) {
        if (isCovered) {
            this.isCovered = false;
        } else {
            // TODO: 11/12/17 (DO onVanishFunction to remove Gift from 2D Array)
        }
    }

    @Override
    public int getHealth() {
        return 1;
    }

    @Override
    public void setPosition(final double x, final double y) {
        this.pos = new Point2D.Double(x, y);
    }

    @Override
    public Point2D getPosition() {
        return this.pos;
    }

    @Override
    public int getVelocity() {
        return 0;
    }

    @Override
    public int getAcceleration() {
        return 0;
    }

    @Override
    public void setVelocity(int velocity) {
    }

    @Override
    public void setAcceleration(int acceleration) {
    }

    @Override
    public Image getImage() {
        return GiftImage.getImage();
    }

    @Override
    public void destroy() {
        /*final URL resource = getClass().getResource("/music/giftSound.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();*/
        //todo el sound aho, uncomment
        getObservers().forEach(n -> ((LifeObserver) n).notifyFuneralOf(Gift.this));
    }

    @Override
    public void revive() {
        getObservers().forEach(n -> ((LifeObserver) n).notifyResurrectionOf(Gift.this));
    }

    @Override
    public List<Observer> getObservers() {
        return observers;
    }

    @Override
    public boolean canObserve(final Observer observer) {
        return observer instanceof LifeObserver;
    }
}
