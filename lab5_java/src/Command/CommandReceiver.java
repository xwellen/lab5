package Command;

import BaseClass.SpaceMarine;
import BaseClass.Weapon;
import CollectonUtils.CollectionManager;
import CollectonUtils.CollectionUtils;
import Command.ConcreteCommand.ExecuteScript;
import Utils.ElementCreator;
import Utils.EnumReaders.WeaponReader;
import Utils.ParserJson;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

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

    public void remove_first(){
        CollectionManager.remove_first();
    }

    public void average_of_health(){
        CollectionManager.average_of_health();
    }

    public void filter_by_health(String stringHealth){
        long targetHealth = Long.parseLong(stringHealth);
        CollectionManager.filter_by_health(targetHealth);
    }

    public void remove_greater() {
        CollectionManager.remove_greater(ElementCreator.createSpaceMarine());
    }

    public void count_greater_than_weapon_type(String stringWeapon){
        try{
            Weapon weapon = WeaponReader.parseWeapon(stringWeapon);
            CollectionManager.count_greater_than_weapon_type(weapon);
        }
        catch (IOException e){
            System.out.println(e.getMessage() + "\n Попробуйте снова");
        }
    }

    public void add_if_min() {
        SpaceMarine spaceMarineToAdd = ElementCreator.createSpaceMarine();
        CollectionManager.addIfMin(spaceMarineToAdd);
    }

    public void executeScript(String path) {
        String line;
        String command;
        ArrayList<String> parameters = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(path)), StandardCharsets.UTF_8))) {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.split(" ")[0].matches("add|update|add_if_min|remove_greater")) {
                    command = line;
                    for (int i = 0; i < 8; i++) {
                        if (line != null) {
                            line = bufferedReader.readLine();
                            parameters.add(line);
                        } else { System.out.println("Не хватает параметров для создания объекта."); break; }
                    }
                    SpaceMarine spaceMarine = ElementCreator.createScriptSpaceMarine(parameters);
                    switch (command.split(" ")[0]) {
                        case "add":
                            CollectionManager.add(spaceMarine);
                            break;
                        case "update":
                            CollectionManager.update(spaceMarine, Long.parseLong(command.split(" ")[1]));
                            break;
                        case "remove_greater":
                            CollectionManager.remove_greater(spaceMarine);
                            break;
                        case "add_if_min":
                            CollectionManager.addIfMin(spaceMarine);
                            break;
                    }
                } else if (line.split(" ")[0].equals("execute_script")
                        && line.split(" ")[1].equals(ExecuteScript.getPath())) { System.out.println("Пресечена попытка рекурсивного вызова скрипта."); }
                else { commandInvoker.executeCommand(line.split(" ")); }
            }
        } catch (IOException e) {
            System.out.println("Ошибка! " + e.getMessage());
        }
    }

    public void save() {
        ParserJson.collectionToJson();
    }

    public void exit() {
        System.out.println("Завершение работы программы.");
        System.exit(0);
    }

}
