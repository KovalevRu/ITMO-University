package command;

import java.io.IOException;
import model.StudyGroup;
import util.StudyGroupInputHandler;

/**
 * Команда для добавления элемента, если его значение меньше минимального.
 */
public class AddIfMinCommand extends AbstractCommand {

    /**
     * Конструктор команды добавления минимального элемента.
     *
     * @param inputHandler обработчик ввода данных
     */
    public AddIfMinCommand(StudyGroupInputHandler inputHandler) {
        super("add_if_min", "добавить новый элемент в коллекцию, если его значение меньше, "
                + "чем у наименьшего элемента этой коллекции", inputHandler);
    }

    /**
     * Выполняет команду добавления элемента в коллекцию, если он меньше минимального.
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
        
        final StudyGroup newGroup = inputHandler.readStudyGroup();
        final StudyGroup minGroup = collection.findMin();

        if (minGroup == null || newGroup.compareTo(minGroup) < 0) {
            collection.add(newGroup);
            return "Элемент успешно добавлен в коллекцию";
        } else {
            return "Элемент не был добавлен, так как он не меньше минимального";
        }
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
