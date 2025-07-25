package command;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Команда для группировки элементов по group admin и вывод их количества.
 */
public class GroupCountingByGroupAdminCommand extends AbstractCommand {

    /**
     * Конструктор команды.
     */
    public GroupCountingByGroupAdminCommand() {
        super("group_counting_by_group_admin", 
              "сгруппировать элементы коллекции по значению поля groupAdmin, "
                + "вывести количество элементов в каждой группе", 
              null);
    }

    /**
     * Выполняет группировку элементов по администратору группы и выводит их количество.
     *
     * @param args аргументы команды (не используются)
     * @return строка с результатами группировки
     */
    @Override
    public String execute(String[] args) {
        if (collection == null) {
            return "Ошибка: коллекция не установлена";
        }
        final Map<String, Long> adminCounts = collection.groupCountingByGroupAdmin();
        
        if (adminCounts.isEmpty()) {
            return "В коллекции нет элементов с администраторами групп";
        }
        
        return adminCounts.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining("\n"));
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
