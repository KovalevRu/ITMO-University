package command;

/**
 * Команда для завершения работы программы.
 */
public class ExitCommand extends AbstractSimpleCommand {

    /**
     * Конструктор команды.
     */
    public ExitCommand() {
        super("exit", "завершить программу");
    }

    /**
     * Выполняет завершение работы программы.
     *
     * @param args аргументы команды (не используются)
     * @return сообщение о завершении работы
     */
    @Override
    public String execute(String[] args) {
        System.exit(0);
        return "Программа завершена";
    }
}
