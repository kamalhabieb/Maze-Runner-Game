package controllers.command;

import models.charcter.states.StateFactory;
import models.charcter.weapons.bullets.BulletImpl;

import static models.charcter.states.Directions.movingSouth;
import static models.charcter.states.Directions.shooting;


public class Shooting implements Command {

    @Override
    public void execute(final Receiver receiver) {
        receiver.setState(StateFactory.getState(shooting));
        receiver.shoot();
    }



}
