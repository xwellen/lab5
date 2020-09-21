package Command.ConcreteCommand;

import Command.Command;
import Command.CommandReceiver;

public class Save extends Command {
    private final CommandReceiver commandReceiver;

    public Save(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде save.");
        }
        commandReceiver.save();
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда save – сохранить коллекцию в файл.");
    }
}
