package Event;

import java.util.Random;

public class EventSimulator {

    /**
     Метод для демонстрации случайного события в номере.
     Возможные события:
     1. Взрыв комнаты.
     2. Срабатывание сигнализации (сбойй или корректная работа).
     3. Отсутствие событий.
     */
    public static void simulateUnexpectedEvent() {
        Random random = new Random();
        int eventChance = random.nextInt(100);

        if (eventChance < 30) {
            System.out.println("Неожиданно в номере раздался грохот!");
            throw new IllegalStateException("Комната взорвалась от неведомой причины!");
        } else if (eventChance < 60) {
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
        } else {
            System.out.println("Ночь проходит спокойно. Дополнительных событий не обнаружено.");
        }
    }
}
