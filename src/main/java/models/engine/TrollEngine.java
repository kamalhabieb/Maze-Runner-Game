package models.engine;

import java.util.Random;

public class TrollEngine extends AbstractEngine{
    public TrollEngine(){
        super.acceleration = 10;
        Random random = new Random();
        int  rand = random.nextInt(3) -1;
        super.horizontalFactor = rand*1;
        super.verticalFactor = rand*1;
        super.modeRate = 1;
    }
}
