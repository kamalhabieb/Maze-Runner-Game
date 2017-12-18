package models.charcter.states;

public class StateFactory {

    public static State getState(Directions Directions) {
        switch (Directions) {
            case shooting :
                return new shootingGun1();
            case movingEast:
                return new MoveEast();
            case movingWest:
                return new MoveWest();
            case movingNorth:
                return new MoveNorth();
            case movingSouth:
                return new MoveSouth();
            default:
                return new Reset();
        }
    }
}
