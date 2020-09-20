package Command;

public abstract class Command {
    protected abstract void writeInfo();
    protected abstract void execute(String[] args);
}
