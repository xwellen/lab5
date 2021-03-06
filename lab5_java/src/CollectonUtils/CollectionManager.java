package CollectonUtils;

import BaseClass.SpaceMarine;
import BaseClass.Weapon;
import Utils.ElementCreator;
import com.sun.deploy.security.SelectableSecurityManager;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class CollectionManager {
    private static LinkedList<SpaceMarine> linkedList;
    private static ZonedDateTime creationDate;

    public static void initList() {
        if (linkedList == null) { linkedList = new LinkedList<>(); creationDate = ZonedDateTime.now(); }
    }

    public static LinkedList<SpaceMarine> getLinkedList() {
        return linkedList;
    }

    public static void add(SpaceMarine spaceMarine) {
        linkedList.add(spaceMarine);
    }


    public static void getInfo() {
        System.out.println("Тип коллекции – " + linkedList.getClass().getName());
        System.out.println("Дата инициализации коллекции – " + creationDate);
        System.out.println("Количество элементов в коллекции – " + linkedList.size());
        System.out.println("_________________________________________________________\n");
    }

    public static void show() {
        linkedList.forEach(CollectionUtils::display);
    }

//    public static void update(StudyGroup groupToUpdate, Integer elementId) {
//        linkedList.forEach(studyGroup -> {
//            if (studyGroup.getId().equals(elementId)) {
//                studyGroup.setName(groupToUpdate.getName());
//                studyGroup.setCoordinates(groupToUpdate.getCoordinates());
//                studyGroup.setStudentsCount(groupToUpdate.getStudentsCount());
//                studyGroup.setFormOfEducation(groupToUpdate.getFormOfEducation());
//                studyGroup.setSemesterEnum(groupToUpdate.getSemesterEnum());
//                studyGroup.setGroupAdmin(groupToUpdate.getGroupAdmin());
//            }
//        });
//    }

    public static void remove_by_id(long marineID) {
        linkedList.forEach(spaceMarine -> {
            if (spaceMarine.getId() == marineID) { linkedList.remove(spaceMarine); }
        });
    }

    public static void clear() {
        linkedList.clear();
    }



    public static void remove_greater(SpaceMarine spaceMarine) {
        linkedList.forEach(listSpaceMarine -> {
            if (listSpaceMarine.compareTo(spaceMarine) > 0) {
                linkedList.remove(listSpaceMarine);
            } else { System.out.println("Таких элементов не найдено"); }
        });
    }

    public static void update(SpaceMarine marineToUpdate, long elementId) {
        linkedList.forEach(spaceMarine -> {
            if (spaceMarine.getId() == elementId) {
                spaceMarine.setName(marineToUpdate.getName());
                spaceMarine.setCoordinates(marineToUpdate.getCoordinates());
                spaceMarine.setHealth(marineToUpdate.getHealth());
                spaceMarine.setCategory(marineToUpdate.getCategory());
                spaceMarine.setWeaponType(marineToUpdate.getWeaponType());
                spaceMarine.setMeleeWeapon(marineToUpdate.getMeleeWeapon());
                spaceMarine.setChapter(marineToUpdate.getChapter());
            }
        });
    }

    public static void remove_first() {
        if (linkedList.size() > 0) { linkedList.remove(0); }
        else { System.out.println("Коллекция пуста."); }
    }

    public static void average_of_health(){
        if (linkedList.size() > 0){
            long healthSum = 0;
            for (SpaceMarine spaceMarine : linkedList) healthSum += spaceMarine.getHealth();
            System.out.println("Среднее значение здоровья: " + healthSum/(double)linkedList.size());
        }
        else {
            System.out.println("Коллекция пуста");
        }
    }

    public static void filter_by_health(long targetHealth){
        if (linkedList.size() > 0){
            int count = 0;
            for (SpaceMarine spaceMarine : linkedList) {
                if (spaceMarine.getHealth() == targetHealth){
                    CollectionUtils.display(spaceMarine);
                    count++;
                }
            }
            if (count == 0) System.out.println("Бойцов с выбранным значением здоровья нет в коллекции");
        }
        else {
            System.out.println("Коллекция пуста");
        }
    }

    public static void count_greater_than_weapon_type(Weapon weapon){
        if (linkedList.size() > 0){
            int count = 0;
            for (SpaceMarine spaceMarine : linkedList){
                if (spaceMarine.getWeaponType() != null && spaceMarine.getWeaponType().compareTo(weapon) > 0) count++;
            }
            System.out.println("Результат: "+ count);
        }
        else {
            System.out.println("Коллекция пуста");
        }
    }

    public static SpaceMarine getMinElement() throws NullPointerException{
        if (linkedList.size() > 0){
            SpaceMarine result = linkedList.getFirst();
            for (SpaceMarine spaceMarine : linkedList){
                if (spaceMarine.compareTo(result) < 0) result = spaceMarine;
            }
            return result;
        }
        else throw new NullPointerException("Коллекция пуста");
    }

    public static void addIfMin(SpaceMarine spaceMarineToAdd){
        try {
            SpaceMarine minElement = CollectionManager.getMinElement();
            if (spaceMarineToAdd.compareTo(minElement) < 0) {
                CollectionManager.add(spaceMarineToAdd);
                System.out.println("Элемент успешно добавлен в коллекцию");
            } else System.out.println("Элемент превосходит минимальный, не добавлен в коллекцию");
        }
        catch (NullPointerException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void addJsonObject(SpaceMarine spaceMarine) {
        spaceMarine.setId(IDGenerator.generateID((int) spaceMarine.getId()));
        linkedList.add(spaceMarine);
    }

}
