package Command.ConcreteCommand;

import Command.Command;
import Command.CommandReceiver;

public class AverageOfHealth extends Command {
    private final CommandReceiver commandReceiver;

    public AverageOfHealth (CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде average_of_health.");
        }
        commandReceiver.average_of_health();
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда average_of_health – вывести среднее значение поля health для всех элементов коллекции");
    }
}
