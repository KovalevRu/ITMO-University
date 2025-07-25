package command;

import java.io.IOException;

/**
 * Базовый интерфейс для всех команд.
 */
public interface Command {

    /**
     * Выполняет команду.
     *
     * @param args аргументы команды
     * @return результат выполнения команды
     * @throws IOException если произошла ошибка ввода/вывода
     */
    String execute(String[] args) throws IOException;
    
    /**
     * Возвращает описание команды.
     *
     * @return описание команды
     */
    String getDescription();
} 
