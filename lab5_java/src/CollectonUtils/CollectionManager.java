package CollectonUtils;

import BaseClass.SpaceMarine;

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

    public static void remove_by_id(Integer marineID) {
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


//    public static void countByGroupAdmin(Person groupAdmin) {
//        System.out.println(linkedList.stream().filter(studyGroup -> studyGroup.getGroupAdmin().equals(groupAdmin)).count());
//    }
}
