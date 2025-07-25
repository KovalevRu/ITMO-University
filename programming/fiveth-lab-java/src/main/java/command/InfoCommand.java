package command;


/**
 * Команда для вывода информации о коллекции.
 */
public class InfoCommand extends AbstractCommand {

    /**
     * Конструктор команды.
     */
    public InfoCommand() {
        super("info", "вывести информацию о коллекции "
                + "(тип, дата инициализации, количество элементов и т.д.)", null);
    }

    /**
     * Выполняет вывод информации о коллекции.
     *
     * @param args аргументы команды (не используются)
     * @return строка с информацией о коллекции
     */
    @Override
    public String execute(String[] args) {
        if (collection == null) {
            return "Ошибка: коллекция не установлена";
        }
        return collection.getCollectionInfo();
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
