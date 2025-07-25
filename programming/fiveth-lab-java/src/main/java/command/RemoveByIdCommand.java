package command;


/**
 * Команда для удаления элемента (группы) по его id.
 */
public class RemoveByIdCommand extends AbstractCommand {

    /**
     * Конструктор команды.
     */
    public RemoveByIdCommand() {
        super("remove_by_id", "удалить элемент из коллекции по его id", null);
    }

    /**
     * Выполняет удаление элемента из коллекции по его ID.
     *
     * @param args аргументы команды (должен быть указан ID элемента)
     * @return сообщение о результате удаления
     */
    @Override
    public String execute(String[] args) {
        if (collection == null) {
            return "Ошибка: коллекция не установлена";
        }
        if (args.length < 1) {
            return "Не указан ID элемента";
        }
        try {
            final int id = Integer.parseInt(args[0]);
            if (collection.removeById(id)) {
                return "Элемент с ID " + id + " удален";
            }
            return "Элемент с ID " + id + " не найден";
        } catch (NumberFormatException e) {
            return "ID должен быть числом";
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
