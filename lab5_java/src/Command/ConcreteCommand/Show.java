package Command.ConcreteCommand;

import Command.Command;
import Command.CommandReceiver;


public class Show extends Command{
    private final CommandReceiver commandReceiver;

    public Show (CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде show.");
        }
        commandReceiver.show();
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда show – вывести все элементы коллекции в строковом представлении.");
    }
}
