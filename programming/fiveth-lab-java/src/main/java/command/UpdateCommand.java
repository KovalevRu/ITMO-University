package command;

import java.io.IOException;
import model.StudyGroup;
import util.StudyGroupInputHandler;

/**
 * Команда для обновления элемента коллекции по ID.
 */
public class UpdateCommand extends AbstractCommand {

    /**
     * Конструктор команды обновления.
     *
     * @param inputHandler обработчик ввода данных
     */
    public UpdateCommand(StudyGroupInputHandler inputHandler) {
        super("update", "обновить значение элемента коллекции, "
                + "id которого равен заданному", inputHandler);
    }

    /**
     * Выполняет команду обновления элемента коллекции.
     *
     * @param args аргументы команды (ID элемента)
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
        
        validateArguments(args, 1);

        try {
            final long id = Long.parseLong(args[0]);
            final StudyGroup existingGroup = collection.findById(id);
            if (existingGroup == null) {
                return "Элемент с ID " + id + " не найден";
            }

            final StudyGroup newGroup = inputHandler.readStudyGroup();
            newGroup.setId(id);
            final int index = collection.indexOf(existingGroup);
            collection.set(index, newGroup);
            return "Элемент успешно обновлен";
        } catch (NumberFormatException e) {
            return "Ошибка: ID должен быть числом";
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
