package models.matter;

import java.awt.*;

public class MyMatter implements Matter {

    private int velocity = 0;
    private int acceleration = 0;
    private Point position = new Point(0,0);
    private String gameMode ;

    public MyMatter(){ // indicates the acceleration of a default game mode
        acceleration = 1;
    }
    public MyMatter(String gameMode){ // modes are to be specified
        this.gameMode = gameMode;
        setAcceleration(this.gameMode);
    }

    @Override
    public void setPosition(int x, int y) {
        position.x = x;
        position.y = y;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public int getVelocity() {
        return velocity;
    }

    @Override
    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    @Override
    public int getAcceleration() {
        return acceleration;
    }

    //set acceleration is now private and depend on the mode

    private void setAcceleration(String gameMode) {
        // switch on different game modes
    }
}
