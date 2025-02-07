package Characters;

import Exceiptions.*;
import Interfaces.*;
import java.util.Objects;

public abstract class Person implements Payable {
    protected String name;
    protected int money;

    public Person(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

     //Абстрактный метод, описывающий действие персонажа.
    public abstract void act();

     //Метод оплаты. Если денег недостаточно, выбрасывается проверяемое исключение.
    @Override
    public void pay(int amount) throws NotEnoughMoneyException {
        if (money < amount) {
            throw new NotEnoughMoneyException(name + " не имеет достаточного количества денег. Требуется: "
                    + amount + ", имеется: " + money);
        }
        money -= amount;
        System.out.println(name + " оплатил " + amount + " сантиков. Остаток: " + money);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return money == person.money && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, money);
    }

    @Override
    public String toString() {
        return name + " (денег: " + money + ")";
    }
}

