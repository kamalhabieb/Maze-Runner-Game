package models.mazeObjects.gift;

import models.charcter.AliveObject;
import models.charcter.Armored;
import models.mazeObjects.Host;

import java.awt.*;

public class AmmoGift extends Gift {
    private int type;

    public AmmoGift(int type, Point pos) {
        if (type > 0 && type < 6) {
            assignGiftType(type);
        } else {
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
        //TODO gift can be visited
        throw new RuntimeException();
    }

    @Override
    public void visit(Host host) {
        try {
            if(!isCovered) {
                Armored armored = (Armored) host;
                armored.affectAmmo(type);
                destroy();
            }
        } catch (ClassCastException e) {
            //TODO handle object is not alive
        }
    }

}
