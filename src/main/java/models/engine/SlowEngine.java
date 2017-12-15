package models.engine;

public class SlowEngine extends AbstractEngine  {
    public SlowEngine(){
        super.acceleration = 1;
        super.horizontalFactor = 1;
        super.verticalFactor = 1;
        super.modeRate = 0.1;
    }
}
