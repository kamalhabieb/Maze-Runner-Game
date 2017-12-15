package models.engine;

public class FastEngine extends AbstractEngine {
    public FastEngine(){
        super.acceleration = 20;
        super.horizontalFactor = 1;
        super.verticalFactor = 1;
        super.modeRate = 3;
    }
}
