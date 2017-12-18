package models.charcter.states;

public class StateFactory {
    public enum state {
        shooting,
        movingEast,
        movingWest,
        movingNorth,
        movingSouth,
        reset
    }

    public static State getState(state state) {
        switch (state) {
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
