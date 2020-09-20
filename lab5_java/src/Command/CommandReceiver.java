package Command;

import CollectonUtils.CollectionManager;
import CollectonUtils.CollectionUtils;
import Utils.ElementCreator;

public class CommandReceiver {
    private final CommandInvoker commandInvoker;

    public CommandReceiver(CommandInvoker commandInvoker) {
        this.commandInvoker = commandInvoker;
    }

    public void add() {
        CollectionManager.add(ElementCreator.createSpaceMarine());
        System.out.println("Элемент добавлен в коллекцию.");
    }

    public void help() {
        commandInvoker.getCommandMap().forEach((name, command) -> command.writeInfo());
    }

    public void info() { // добавить класс
        CollectionManager.getInfo();
    }

    public void show() {
        CollectionManager.show();
    }

    public void clear(){
        CollectionManager.clear();
    }

    public void remove_by_id(String ID) {
        long marineID;
        try {
            marineID = Long.parseLong(ID);
            if (CollectionUtils.checkExist(marineID)) {
                CollectionManager.remove_by_id(marineID);
                System.out.println("Элемент с ID " + marineID + " успешно удален из коллекции.");
            } else {System.out.println("Элемента с таким ID нет в коллекции.");}
        } catch (NumberFormatException e) {
            System.out.println("Команда не выполнена. Вы ввели некорректный аргумент.");
        }
    }

    public void update(String ID) {
        long marineID;
        try {
            marineID = Long.parseLong(ID);
            if (CollectionUtils.checkExist(marineID)) { CollectionManager.update(ElementCreator.createSpaceMarine(), marineID); }
            else {System.out.println("Элемента с таким ID нет в коллекции.");}
        } catch (NumberFormatException e) {
            System.out.println("Команда не выполнена. Вы ввели некорректный аргумент.");
        }
    }

    public void exit() {
        System.out.println("Завершение работы программы.");
        System.exit(0);
    }

}
