package command;

import model.StudyGroup;

/**
 * Команда для удаления первого элемента коллекции.
 */
public class RemoveFirstCommand extends AbstractCommand {

    /**
     * Конструктор команды.
     */
    public RemoveFirstCommand() {
        super("remove_first", "удалить первый элемент из коллекции", null);
    }

    /**
     * Выполняет удаление первого элемента коллекции.
     *
     * @param args аргументы команды (не используются)
     * @return сообщение о результате удаления
     */
    @Override
    public String execute(String[] args) {
        if (collection == null) {
            return "Ошибка: коллекция не установлена";
        }
        final StudyGroup first = collection.getFirst();
        if (first != null) {
            collection.removeById(first.getId());
            return "Первый элемент коллекции удален";
        }
        return "Коллекция пуста";
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
