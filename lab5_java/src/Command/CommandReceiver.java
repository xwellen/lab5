package Command;

public class CommandReceiver {
    private final CommandInvoker commandInvoker;

    public CommandReceiver(CommandInvoker commandInvoker) {
        this.commandInvoker = commandInvoker;
    }

    public void help() {
        commandInvoker.getCommandMap().forEach((name, command) -> command.writeInfo());
    }

//    public void info() { // добавить класс
//        CollectionManager.getInfo();
//    }

//    public void show() {
//        CollectionManager.show();
//    }

    public void exit() {
        System.out.println("Завершение работы программы.");
        System.exit(0);
    }

}
