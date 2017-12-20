package models.engine;

public class AbstractEngine implements Engine {

    private double xDistance = 0;
    private double yDistance = 0;
    protected int acceleration = 0;
    private int velocity = 0;
    protected double modeRate = 0;
    protected double horizontalFactor = 0;
    protected double verticalFactor = 0;


    @Override
    public void moveNorth(Matter object) {
        getInfo(object);
        yDistance = (yDistance - verticalFactor * (velocity * modeRate + 0.5 * acceleration * modeRate));
        // x = vo *t+0.5*a*t^2
        object.setPosition(xDistance, yDistance);
        velocity += acceleration;//v = vo + a
        //object.setVelocity(velocity);
    }

    @Override
    public void moveEast(Matter object) {
        getInfo(object);
        xDistance = (xDistance + horizontalFactor * (velocity * modeRate + 0.5 * acceleration * modeRate));
        // x = vo *t+0.5*a*t^2
        object.setPosition(xDistance, yDistance);
        velocity += acceleration;//v = vo + a
        //System.out.println(velocity);
        //object.setVelocity(velocity);
    }

    @Override
    public void moveSouth(Matter object) {
        getInfo(object);
        yDistance = (yDistance + verticalFactor * (velocity * modeRate + 0.5 * acceleration * modeRate));
        // x = vo *t+0.5*a*t^2
        object.setPosition(xDistance, yDistance);
        velocity += acceleration;//v = vo + a
        // object.setVelocity(velocity);
    }

    @Override
    public void moveWest(Matter object) {
        getInfo(object);
        xDistance = (xDistance - horizontalFactor * (velocity * modeRate +
                0.5 * acceleration * modeRate));
        // x = vo *t+0.5*a*t^2
        object.setPosition(xDistance, yDistance);
        velocity += acceleration;//v = vo + a
        //object.setVelocity(velocity);
    }

    @Override
    public void stop(Matter object) {
        //   object.setVelocity(0);
    }

    private void getInfo(Matter object) {
        xDistance = object.getPosition().getX();
        yDistance = object.getPosition().getY();
        velocity = object.getVelocity();
        object.setAcceleration(acceleration);
    }
}
