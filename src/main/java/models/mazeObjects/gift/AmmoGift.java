package models.mazeObjects.gift;

import models.charcter.Person;
import models.mazeObjects.Host;
import models.mazeObjects.Visitor;

import java.util.Random;

public class AmmoGift extends Gift implements Visitor {
    private int type;
    public AmmoGift(int type) {
        if(type > 0 && type < 6) {
            assignGiftType(type);
        }
        else {
           throw new RuntimeException();
        }

    }
    @Override
    public void assignGiftType(int giftType) {
        this.type = giftType;
    }

    @Override
    public boolean affectHealthBy(int effect) {
        throw new RuntimeException();
    }

    @Override
    public void visit(Host host) {
        host.affectAmmo(type);
    }

    @Override
    public void affectAmmo(int ammo) {
        throw new RuntimeException();
    }
}
