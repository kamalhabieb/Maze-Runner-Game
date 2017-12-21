package models.charcter.states;

public class StateFactory {

    public static State getState(Directions Directions) {
        switch (Directions) {
            case movingEast:
                return new MoveEast();
            case movingWest:
                return new MoveWest();
            case movingNorth:
                return new MoveNorth();
            case movingSouth:
                return new MoveSouth();
            case die:
                return new Die();
            case win:
                return new Win();
            default:
                return new Reset();
        }
    }
}
