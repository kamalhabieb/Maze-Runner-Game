package models.mazeObjects.bomb;

import models.charcter.AliveObject;
import models.engine.Matter;
import models.mazeObjects.Host;
import models.mazeObjects.Visitor;

import java.awt.*;

public class Bomb implements Bomb_I, Visitor, Host, Matter, AliveObject {
    private int type;
    private int damageRate;
    private int timer;
    private Point position;
    private long explodeTime;
    private int health = 1;
    private long triggeringStartTime;
    private boolean isCovered = true;

    public Bomb(int type, Point pos) {
        if (type == 0) {
            type = 1;
        }
        this.type = type;
        setDamageRate(20 * type);
        if (getDamageRate() == 0) {
            setDamageRate(20);
        }
        setTimer(type);
        if (getTimer() > 5) {
            setTimer(5);
        }

        setPosition(pos.x, pos.y);
    }

    @Override
    public int getDamageRate() {
        return this.damageRate;
    }

    @Override
    public void setDamageRate(int damageValue) {
        this.damageRate = damageValue;
    }

    @Override
    public int getTimer() {
        return this.timer;
    }

    @Override
    public void setTimer(int time) {
        this.timer = time;
    }

    @Override
    public Point getPosition() {
        return this.position;
    }

    @Override
    public int getVelocity() {
        return 0;
    }

    @Override
    public void setVelocity(int velocity) {

    }

    @Override
    public int getAcceleration() {
        return 0;
    }

    @Override
    public void setAcceleration(int acceleration) {
    }

    @Override
    public void setPosition(int x, int y) {
        this.position = new Point(x, y);
    }

    @Override
    public void explode() {
        //mario, e3mel el function di 3andak 3ashan mesh had5ol classak 3ashan el conflicts
        // TODO: 11/12/17
        /*ArrayList<MazeObject> affectedCells = ControlTower.getAffectedRigion(type, getPosition());
        for(int i = 0; i < affectedCells.size(); i++) {
            this.visit(affectedCells.get(i));
        }*/
        // TODO: 11/12/17 (DO onExplodeFunction to remove bomb from 2D Array)
    }

    @Override
    public long getExplodeTime() {
        return this.explodeTime;
    }

    @Override
    public void setExplodeTime(long explodeTime) {
        this.explodeTime = explodeTime;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }


    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public boolean affectHealthBy(int effect) {
        this.health = 0;
        this.isCovered = false;
        // TODO: 11/12/17
        //by update method, if currentTime-beginTime >= timer {Do this.explode}
        this.triggeringStartTime = System.currentTimeMillis();
        return true;
    }

    @Override
    public void visit(Host host) {
        try{
            AliveObject aliveObject = (AliveObject) host;
            aliveObject.affectHealthBy(this.getDamageRate());
        } catch (ClassCastException e){
            //TODO handle object is not alive
        }

    }
}