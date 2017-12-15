package models.engine;

public class BlindEngine extends AbstractEngine {
    public BlindEngine(){
        super.acceleration = 10;
        super.horizontalFactor = 1;
        super.verticalFactor = 1;
        super.modeRate = 1;
    }
}
