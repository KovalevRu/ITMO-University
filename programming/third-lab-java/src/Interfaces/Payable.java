package Interfaces;

import Exceiptions.NotEnoughMoneyException;

public interface Payable {
    //Метод оплаты указанной суммы. Если денег недостаточно – выбрасывается исключение
    void pay(int amount) throws NotEnoughMoneyException;
}
