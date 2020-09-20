import Command.CommandInvoker;
import Command.CommandReceiver;
import Command.ConcreteCommand.Exit;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleManager {
     void startInteractiveMode() throws IOException {
        CommandInvoker commandInvoker = new CommandInvoker();
        CommandReceiver commandReceiver = new CommandReceiver(commandInvoker);
        //CollectionManager.initList();


        commandInvoker.register("exit", new Exit(commandReceiver));


        try(Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                commandInvoker.executeCommand(scanner.nextLine().trim().split(" "));
            }
        }
    }
}
