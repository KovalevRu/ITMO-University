package Action;
import Objects.Person;

public class MoneyChecker {
    // Хватает ли в сумме на номер
    public boolean hasEnoughMoney(Person person1, Person person2, int requiredAmount) {
        // Получаем деньги у первого
        int moneyPerson1 = person1.getMoney();

        // Получаем деньги у второго
        int moneyPerson2 = person2.getMoney();

        // Сумма
        int totalMoney = moneyPerson1 + moneyPerson2;

        // Сравниваем общую сумму с требуемой
        return totalMoney >= requiredAmount;
    }
}
