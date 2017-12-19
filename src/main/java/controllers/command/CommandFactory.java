package controllers.command;
public class CommandFactory {
    public enum commands {
        shootABullet,
        moveEast,
        moveWest,
        moveSouth,
        moveNorth,
        idle
    }

    public static Command getCommand(commands command) {
        switch (command) {
            case shootABullet:
                return new Shooting();
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