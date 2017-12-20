package models.mazeObjects.gift;

import models.charcter.AliveObject;
import models.mazeObjects.Host;
import models.mazeObjects.Visitor;

import java.awt.*;

public class HealthGift  extends Gift implements Visitor{
    private int type;
    public HealthGift(int type, Point pos) {
        if(type > 0 && type < 6) {
            assignGiftType(type);
        }
        else {
            throw new RuntimeException();
        }
        this.setPosition(pos.x, pos.y);
    }
    @Override
    public void assignGiftType(int giftType) {
        this.type = giftType;
    }

    @Override
    public boolean affectHealthBy(int effect) {
        if (isCovered) {
            this.isCovered = false;
            return false;
        } else {
            destroy();
            return true;
        }
    }


    @Override
    public void visit(Host host) {
        try{
            if(!isCovered) {
                AliveObject aliveObject = (AliveObject) host;
                aliveObject.affectHealthBy(type * 20);
                destroy();
            }
        }catch (ClassCastException e){
            //TODO handle the host is not alive
        }
    }
}
