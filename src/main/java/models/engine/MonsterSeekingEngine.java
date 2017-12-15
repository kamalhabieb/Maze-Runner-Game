package models.engine;

public class MonsterSeekingEngine extends AbstractEngine {
    public MonsterSeekingEngine(){
        super.acceleration = 10;
        super.horizontalFactor = 1;
        super.verticalFactor = 1;
        super.modeRate = 1;
    }
}
