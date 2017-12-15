package models.charcter.states;

public class StateFactory {
    public enum state {
        movingEast,
        movingWest,
        movingNorth,
        movingSouth,
        reset
    }

    public static State getState(state state) {
        switch (state) {
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
