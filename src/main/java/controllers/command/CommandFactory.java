package controllers.command;

public class CommandFactory {
    public enum commands {
        moveEast,
        moveWest,
        moveSouth,
        moveNorth,
        idle
    }

    public static Command getCommand(commands command) {
        switch (command) {
            case moveEast:
                return new MoveEastCommand();
            case moveWest:
                return new MoveWestCommand();
            case moveSouth:
                return new MoveSouthCommand();
            case moveNorth:
                return new MoveNorthCommand();
            case idle:
            default:
                return new MoveIdleCommand();

        }
    }
}
