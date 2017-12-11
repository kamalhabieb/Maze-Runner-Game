package models.engine;

public class BlindEngine implements Engine {

    private int xDistance = 0;
    private int yDistance = 0;
    private int acceleration = 0;
    private int velocity = 0;


    @Override
    public void moveNorth(Matter object) {

    }

    @Override
    public void moveEast(Matter object) {

    }

    @Override
    public void moveSouth(Matter object) {

    }

    @Override
    public void moveWest(Matter object) {

    }
    private void getInfo(Matter object){
        xDistance = object.getPosition().x;
        yDistance = object.getPosition().y;
        velocity = object.getVelocity();
        /**
         * this engine is treated as a default engine
         * in acceleration = 10
         */
        object.setAcceleration(10);
        acceleration = object.getAcceleration();
    }
    @Override
    public void stop(Matter object){
        object.setVelocity(0);
    }
}
