package controllers.command;

public interface Command {
    void execute(Receiver receiver);
}
