package models.engine;

public class DefaultEngine extends AbstractEngine {
    public DefaultEngine() {
        super.acceleration = 2;
        super.horizontalFactor = 1;
        super.verticalFactor = 1;
        super.modeRate = 1;
    }
}
