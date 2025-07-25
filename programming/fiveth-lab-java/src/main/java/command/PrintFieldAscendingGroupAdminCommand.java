package command;


/**
 * Команда для вывода groupAdmin в порядке возрастания.
 */
public class PrintFieldAscendingGroupAdminCommand extends AbstractCommand {

    /**
     * Конструктор команды.
     */
    public PrintFieldAscendingGroupAdminCommand() {
        super("print_field_ascending_group_admin", 
              "вывести значения поля groupAdmin всех элементов в порядке возрастания", 
              null);
    }

    /**
     * Выполняет вывод значений поля groupAdmin в порядке возрастания.
     *
     * @param args аргументы команды (не используются)
     * @return строка со значениями поля groupAdmin в порядке возрастания
     */
    @Override
    public String execute(String[] args) {
        if (collection == null) {
            return "Ошибка: коллекция не установлена";
        }
        final String result = collection.getGroupAdminFieldAscending();
        if (result.isEmpty()) {
            return "В коллекции нет элементов с администраторами групп";
        }
        return result;
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
