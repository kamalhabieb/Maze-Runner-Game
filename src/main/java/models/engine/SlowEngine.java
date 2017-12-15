package models.engine;

public class SlowEngine implements Engine  {
    private int xDistance = 0;
    private int yDistance = 0;
    private int acceleration = 0;
    private int velocity = 0;

    @Override
    public void moveNorth(Matter object) {
        getInfo(object);
        yDistance+= velocity + 0.5* acceleration;// x = vo *t+0.5*a*t^2
        object.setPosition(xDistance,yDistance);
        velocity += acceleration;//v = vo + a
        object.setVelocity(velocity);
    }

    @Override
    public void moveEast(Matter object) {
        getInfo(object);
        xDistance+= velocity + 0.5* acceleration;// x = vo *t+0.5*a*t^2
        object.setPosition(xDistance,yDistance);
        velocity += acceleration;//v = vo + a
        object.setVelocity(velocity);
    }

    @Override
    public void moveSouth(Matter object) {
        getInfo(object);
        yDistance-= velocity + 0.5* acceleration;// x = vo *t+0.5*a*t^2
        object.setPosition(xDistance,yDistance);
        velocity += acceleration;//v = vo + a
        object.setVelocity(velocity);
    }

    @Override
    public void moveWest(Matter object) {
        getInfo(object);
        xDistance-= velocity + 0.5* acceleration;// x = vo *t+0.5*a*t^2
        object.setPosition(xDistance,yDistance);
        velocity += acceleration;//v = vo + a
        object.setVelocity(velocity);
    }
    private void getInfo(Matter object){
        xDistance = object.getPosition().x;
        yDistance = object.getPosition().y;
        velocity = object.getVelocity();
        /** This means the acceleration in the
         * SlowEngine equals 1 for all characters
         */
        object.setAcceleration(1);
        acceleration = object.getAcceleration();
    }
    public void stop(Matter object){
        object.setVelocity(0);
    }
}