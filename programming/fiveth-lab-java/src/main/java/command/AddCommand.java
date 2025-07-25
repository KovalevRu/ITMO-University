package command;

import java.io.IOException;
import model.StudyGroup;
import util.StudyGroupInputHandler;

/**
 * Команда для добавления нового элемента в коллекцию.
 */
public class AddCommand extends AbstractCommand {

    /**
     * Конструктор команды добавления.
     *
     * @param inputHandler обработчик ввода данных
     */
    public AddCommand(StudyGroupInputHandler inputHandler) {
        super("add", "добавить новый элемент в коллекцию", inputHandler);
    }

    /**
     * Выполняет команду добавления элемента в коллекцию.
     *
     * @param args аргументы команды (не используются)
     * @return результат выполнения команды
     * @throws IOException если произошла ошибка ввода/вывода
     */
    @Override
    public String execute(String[] args) throws IOException {
        if (collection == null) {
            return "Ошибка: коллекция не установлена";
        }
        if (inputHandler == null) {
            return "Ошибка: обработчик ввода не установлен";
        }
        final StudyGroup group = inputHandler.readStudyGroup();
        collection.add(group);
        return "Элемент успешно добавлен в коллекцию";
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
}
