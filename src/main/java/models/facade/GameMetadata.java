package models.facade;

import java.awt.geom.Point2D;

public class GameMetadata {
    private int health;
    private int ammo;
    private int lives;
    private int score;
    private Point2D playerPosition;

    public int getHealth() {
        return health;
    }

    public void setHealth(final int health) {
        this.health = health;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(final int ammo) {
        this.ammo = ammo;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(final int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(final int score) {
        this.score = score;
    }

    public Point2D getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(final Point2D playerPosition) {
        this.playerPosition = playerPosition;
    }
}
