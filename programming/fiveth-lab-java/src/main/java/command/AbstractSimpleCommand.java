package command;

import java.io.IOException;

/**
 * Абстрактный класс для простых команд, не требующих коллекцию.
 */
public abstract class AbstractSimpleCommand implements Command {

    protected final String name;
    protected final String description;

    /**
     * Конструктор простой команды.
     *
     * @param name имя команды
     * @param description описание команды
     */
    public AbstractSimpleCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Возвращает описание команды.
     *
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Выполняет команду.
     *
     * @param args аргументы команды
     * @return результат выполнения команды
     * @throws IOException если произошла ошибка ввода/вывода
     */
    @Override
    public abstract String execute(String[] args) throws IOException;

    /**
     * Возвращает имя команды.
     *
     * @return имя команды
     */
    public String getName() {
        return name;
    }

    /**
     * Проверяет количество аргументов команды.
     *
     * @param args аргументы команды
     * @param expectedCount ожидаемое количество аргументов
     * @throws IllegalArgumentException если количество аргументов не соответствует ожидаемому
     */
    protected void validateArguments(String[] args, int expectedCount) {
        if (args.length != expectedCount) {
            throw new IllegalArgumentException(
                    String.format("Ожидается %d аргумент(ов), получено %d", 
                            expectedCount, args.length));
        }
    }
} 
