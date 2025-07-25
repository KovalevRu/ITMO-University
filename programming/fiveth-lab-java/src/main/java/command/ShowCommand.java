package command;


/**
 * Команда для вывода всех элементов коллекции.
 */
public class ShowCommand extends AbstractCommand {

    /**
     * Конструктор команды.
     */
    public ShowCommand() {
        super("show", "вывести все элементы коллекции", null);
    }

    /**
     * Выполняет вывод всех элементов коллекции.
     *
     * @param args аргументы команды (не используются)
     * @return строка с элементами коллекции
     */
    @Override
    public String execute(String[] args) {
        if (collection == null) {
            return "Ошибка: коллекция не установлена";
        }
        return collection.getCollectionAsString();
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
