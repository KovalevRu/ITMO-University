package command;


/**
 * Команда для подсчета количества групп с заданным количеством студентов.
 */
public class CountByStudentsCountCommand extends AbstractCommand {

    /**
     * Конструктор команды.
     */
    public CountByStudentsCountCommand() {
        super("count_by_students_count", 
              "вывести количество элементов, значение поля studentsCount которых равно заданному", 
              null);
    }

    /**
     * Выполняет подсчет количества групп с заданным количеством студентов.
     *
     * @param args аргументы команды (должно быть указано количество студентов)
     * @return сообщение с результатом подсчета
     */
    @Override
    public String execute(String[] args) {
        if (collection == null) {
            return "Ошибка: коллекция не установлена";
        }
        if (args.length < 1) {
            return "Не указано количество студентов";
        }
        try {
            final long studentsCount = Long.parseLong(args[0]);
            final int count = collection.countByStudentsCount(studentsCount);
            return "Количество групп с " + studentsCount + " студентами: " + count;
        } catch (NumberFormatException e) {
            return "Количество студентов должно быть числом";
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
