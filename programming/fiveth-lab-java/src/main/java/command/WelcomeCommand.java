package command;

/**
 * Команда для вывода Welcome-сообщения.
 */
public class WelcomeCommand extends AbstractSimpleCommand {

    /**
     * Конструктор команды.
     */
    public WelcomeCommand() {
        super("welcome", "вывести приветственное сообщение");
    }

    @Override
    public String execute(String[] args) {
        return "Добро пожаловать в систему управления учебными группами!\n"
                + "Доступные команды:\n"
                + "- help - показать список всех команд\n"
                + "- add - добавить новую группу\n"
                + "- update - обновить существующую группу\n"
                + "- show - показать все группы\n"
                + "- save - сохранить изменения\n"
                + "- exit - выход из программы\n"
                + "Введите 'help' для получения полного списка команд\n";
    }
}
