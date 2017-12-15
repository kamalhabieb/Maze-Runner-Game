package models.charcter;

import javafx.scene.image.Image;
import models.facade.ControlTower;

import java.awt.*;

public class Player extends Person {
    private int animationIndex = 0;
    private int width;

    public Player(final ControlTower controlTower) {
        super(controlTower);
    }

    @Override
    public void setPosition(final int x, final int y) {
        if (controlTower.grantPermission(this, new Point(x, y))) {
            super.setPosition(x, y);
        }
    }

    @Override
    public Image getImage() {
        Image image = state.getImage();
        this.width = (int) image.getWidth();
        if (this.isAnimated()) setCoordinates();
        return image;
    }

    private void setCoordinates() {
        animationIndex = (animationIndex + 1) % (width / getAnimationWidth());
        setSrcX(getSrcX() + animationIndex * getAnimationWidth());
    }
}
