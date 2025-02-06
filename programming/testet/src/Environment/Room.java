package Environment;

import java.util.List;

public record Room(String roomNumber, List<Item> items) {
     //Метод для отображения списка предметов в номере.
    public void displayRoomItems() {
        System.out.println("Содержимое номера " + roomNumber + ":");
        Runnable displayTask = new Runnable() {
            @Override
            public void run() {
                for (Item item : items) {
                    System.out.println("- " + item);
                }
            }
        };
        displayTask.run();
        System.out.println("В номере зазвонил звонок! На панели появился мигающий красный глазок.");
        System.out.println("Из отверстия в стене высунулся металлический язычок, а под ним замигала надпись: \"Сантик\".");
    }
}
