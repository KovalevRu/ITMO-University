package command;


/**
 * Команда для очистки коллекции.
 */
public class ClearCommand extends AbstractCommand {

    /**
     * Конструктор команды.
     */
    public ClearCommand() {
        super("clear", "очистить коллекцию", null);
    }

    /**
     * Выполняет очистку коллекции.
     *
     * @param args аргументы команды (не используются)
     * @return сообщение об успешной очистке
     */
    @Override
    public String execute(String[] args) {
        if (collection == null) {
            return "Ошибка: коллекция не установлена";
        }
        collection.clear();
        return "Коллекция успешно очищена";
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
