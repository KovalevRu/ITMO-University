import java.util.ArrayList;
import java.util.List;
import java.util.Random;



import Characters.*;
import Exceiptions.*;
import Environment.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Начало сценария: Ночь в гостинице \"Экономическая\".");

        // Создаем гостиницу с ценой номера 50 сантиков
        Hotel hotel = new Hotel("Экономическая", 50);

        // Создаем персонажей с начальными деньгами: Незнайка - 30, Козлик - 20.
        // Можно менять эти значения для получения вариативного сценария.
        List<Person> guests = new ArrayList<>();
        guests.add(new Neznaika("Незнайка", 30));
        guests.add(new Kozlik("Козлик", 20));
        System.out.println("Гости, пришедшие в гостиницу: " + guests);

        // Проводим проверку и оплату номера (групповая оплата)
        try {
            hotel.checkIn(guests);
        } catch (NotEnoughMoneyException e) {
            System.out.println("Ошибка при оплате номера: " + e.getMessage());
            return;
        }

        // Создаем номер и добавляем предметы
        List<Item> items = new ArrayList<>();
        items.add(new Item(RoomItem.TABLE, "Стол"));
        items.add(new Item(RoomItem.CHAIR, "Стул"));
        items.add(new Item(RoomItem.CHAIR, "Стул"));
        items.add(new Item(RoomItem.CHAIR, "Стул"));
        items.add(new Item(RoomItem.WARDROBE, "Платяной шкаф"));
        items.add(new Item(RoomItem.SINK_WITH_MIRROR, "Рукомойник с зеркалом"));
        items.add(new Item(RoomItem.TV, "Телевизор в углу"));

        // Создаем объект номера (record Room)
        Room room = new Room("101", items);
        System.out.println("Гости получили номер: " + room);

        // Отображаем состояние номера (предметы) и симулируем звонок "Сантик"
        room.displayRoomItems();

        // Демонстрация обработки неожиданного события: сигнализация или взрыв комнаты.
        try {
            simulateUnexpectedEvent();
        } catch (RuntimeException e) {
            System.out.println("Поймано неожиданное исключение: " + e.getMessage());
        }

        System.out.println("Сценарий завершен.");
    }

    /**
     * Метод для демонстрации случайного события в номере.
     * Возможные события:
     * 1. Срабатывание сигнализации: либо корректное, либо с выбросом unchecked исключения.
     * 2. «Взрыв» комнаты – имитируется выбросом unchecked исключения.
     * 3. Никакого события.
     */
    private static void simulateUnexpectedEvent() {
        Random random = new Random();
        int eventChance = random.nextInt(100);

        if (eventChance < 30) { // 30% шанс на взрыв комнаты
            System.out.println("Неожиданно в номере раздался грохот!");
            throw new IllegalStateException("Комната взорвалась от неведомой причины!");
        } else if (eventChance < 60) { // следующие 30% шанс – событие сигнализации
            Runnable alarmEvent = new Runnable() {
                @Override
                public void run() {
                    System.out.println("В номере начал мигать красный глазок. Запускается сигнализация...");
                    if (random.nextBoolean()) {
                        throw new IllegalStateException("Сбой в системе сигнализации!");
                    } else {
                        System.out.println("Сигнализация сработала корректно.");
                    }
                }
            };
            alarmEvent.run();
        } else { // 40% шанс – событий не происходит
            System.out.println("Ночь проходит спокойно. Дополнительных событий не обнаружено.");
        }
    }
}

