package command;

import java.io.IOException;
import model.StudyGroupCollection;
import util.StudyGroupInputHandler;

/**
 * Абстрактный класс для команд, работающих с коллекцией.
 */
public abstract class AbstractCommand implements CollectionCommand {

    protected final String name;
    protected final String description;
    protected final StudyGroupInputHandler inputHandler;
    protected StudyGroupCollection collection;

    /**
     * Конструктор абстрактной команды.
     *
     * @param name имя команды
     * @param description описание команды
     * @param inputHandler обработчик ввода данных
     */
    public AbstractCommand(String name, String description, StudyGroupInputHandler inputHandler) {
        this.name = name;
        this.description = description;
        this.inputHandler = inputHandler;
    }

    /**
     * Возвращает обработчик ввода данных.
     *
     * @return обработчик ввода данных
     */
    protected StudyGroupInputHandler getInputHandler() {
        return inputHandler;
    }

    /**
     * Возвращает описание команды.
     *
     * @return описание команды
     */
    @Override
    public abstract String getDescription();

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
     * Устанавливает коллекцию для команды.
     *
     * @param collection коллекция для выполнения команды
     */
    @Override
    public void setCollection(StudyGroupCollection collection) {
        this.collection = collection;
    }

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
