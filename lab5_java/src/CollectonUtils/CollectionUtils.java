package CollectonUtils;

import BaseClass.SpaceMarine;

public class CollectionUtils {
    public static boolean checkExist(long ID) {
        for (SpaceMarine spaceMarine:CollectionManager.getLinkedList()) {
            return spaceMarine.getId() == ID;
        }
        return false;
    }

    static void display(SpaceMarine spaceMarine) {
        System.out.println("ID элемента коллекции – " + spaceMarine.getId());
        System.out.println("Имя бойца – " + spaceMarine.getName());
        System.out.println("Координата X – " + spaceMarine.getCoordinates().getX());
        System.out.println("Координата Y – " + spaceMarine.getCoordinates().getY());
        System.out.println("Дата и время создания элемента – " + spaceMarine.getCreationDate());
        System.out.println("Здоровье – " + spaceMarine.getHealth());
        System.out.println("Звание – " + spaceMarine.getCategory());
        System.out.println("Оружие – " + spaceMarine.getWeaponType());
        System.out.println("Оружие бижнего действия – " + spaceMarine.getMeleeWeapon());
        System.out.println("Расположение – " + spaceMarine.getChapter().getName() + "@" + spaceMarine.getChapter().getWorld());
        System.out.println("_________________________________________________________\n");
    }
}
