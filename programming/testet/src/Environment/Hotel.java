package Environment;

import java.util.List;
import java.util.Objects;


import Exceiptions.*;
import Characters.*;

public class Hotel {
    private final String name;
    private final int roomPrice;

    public Hotel(String name, int roomPrice) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Название гостиницы не может быть пустым.");
        }
        if (roomPrice <= 0) {
            throw new IllegalArgumentException("Стоимость номера должна быть положительной.");
        }
        this.name = name;
        this.roomPrice = roomPrice;
    }


     // Метод для проверки и проведения групповой оплаты за номер.

    public void checkIn(List<Person> guests) throws NotEnoughMoneyException {
        System.out.println("Гости пришли в гостиницу \"" + name + "\". Стоимость номера: " + roomPrice + " сантиков.");
        int totalMoney = guests.stream().mapToInt(Person::getMoney).sum();
        if (totalMoney < roomPrice) {
            throw new NotEnoughMoneyException("Общая сумма денег (" + totalMoney + ") меньше стоимости номера (" + roomPrice + ").");
        }
        System.out.println("Общая сумма денег гостей достаточна для оплаты номера (" + totalMoney + " сантиков).");

        int remaining = roomPrice;
        for (Person guest : guests) {
            int contribution = Math.min(guest.getMoney(), remaining);
            guest.pay(contribution);
            remaining -= contribution;
            System.out.println(guest.getName() + " внёс " + contribution + " сантиков. Осталось доплатить: " + remaining);
            if (remaining == 0) {
                break;
            }
        }
        System.out.println("Номер оплачен. Гости получают ключ.");

        for (Person guest : guests) {
            guest.act();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel hotel)) return false;
        return roomPrice == hotel.roomPrice && Objects.equals(name, hotel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, roomPrice);
    }

    @Override
    public String toString() {
        return "Гостиница \"" + name + "\", стоимость номера: " + roomPrice + " сантиков";
    }
}
