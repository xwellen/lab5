import CollectonUtils.CollectionManager;
import Command.CommandInvoker;
import Command.CommandReceiver;
import Command.ConcreteCommand.*;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleManager {
     void startInteractiveMode() throws IOException {
        CommandInvoker commandInvoker = new CommandInvoker();
        CommandReceiver commandReceiver = new CommandReceiver(commandInvoker);
        CollectionManager.initList();


        commandInvoker.register("exit", new Exit(commandReceiver));
        commandInvoker.register("info", new Info(commandReceiver));
        commandInvoker.register("show", new Show(commandReceiver));
        commandInvoker.register("help", new Help(commandReceiver));
        commandInvoker.register("add", new Add(commandReceiver));
        commandInvoker.register("clear", new Clear(commandReceiver));
        commandInvoker.register("remove_by_id", new RemoveByID(commandReceiver));
        commandInvoker.register("update", new Update(commandReceiver));
        commandInvoker.register("remove_first", new RemoveFirst(commandReceiver));
        commandInvoker.register("average_of_health", new AverageOfHealth(commandReceiver));

        System.out.println("number of commands:  " + commandInvoker.getCommandMap().size());


        try(Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                commandInvoker.executeCommand(scanner.nextLine().trim().split(" "));
            }
        }
    }
}
