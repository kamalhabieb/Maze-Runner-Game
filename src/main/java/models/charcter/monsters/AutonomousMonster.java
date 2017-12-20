package models.charcter.monsters;

import models.charcter.autonomous.Moth;
import models.charcter.states.Directions;
import models.charcter.states.State;
import models.charcter.states.StateFactory;
import models.engine.Engine;
import models.facade.ControlTower;
import models.search.Path;

import java.awt.geom.Point2D;

public abstract class AutonomousMonster extends Monster implements Moth {
    private Path path;
    private boolean started = false;

    public AutonomousMonster(final ControlTower controlTower) {
        super(controlTower);
        setVelocity(1);
    }

    @Override
    public void setPosition(final double x, final double y) {
        if (controlTower.grantPermission(this, new Point2D.Double(x, y))) {
            super.setPosition(x, y);
        }

    }

    @Override
    public void setPathToFlame(final Path path) {
        this.path = path;
    }

    public void goToFlame() {
        if (path == null) {
            setState(StateFactory.getState(Directions.reset));
            return;
        }
        Point2D next = path.getNextTarget();
        if (next != null && (!started || next.distance(getPosition().getX() / 30, getPosition().getY() / 30) == 0)) {
            setState(StateFactory.getState(path.getNextDirection()));
            started = true;
        }
        if(next == null){
            setState(StateFactory.getState(Directions.reset));
        }
    }

    @Override
    public void update(final Engine engine) {
        goToFlame();
        super.update(engine);
    }

    @Override
    public void setState(final State state) {
        super.setState(state);
        if (state.getType().equals(Directions.reset)) {
            controlTower.drawToFlame(this);
        }
    }
}
