package command;

import java.io.IOException;
import java.util.Map;

/**
 * Команда для вывода справки по доступным командам.
 */
public class HelpCommand extends AbstractSimpleCommand {

    private final Map<String, Command> commands;

    /**
     * Конструктор команды help.
     *
     * @param commands карта доступных команд
     */
    public HelpCommand(Map<String, Command> commands) {
        super("help", "вывести справку по доступным командам");
        this.commands = commands;
    }

    /**
     * Выполняет вывод справки по доступным командам.
     *
     * @param args аргументы команды (не используются)
     * @return строка с описанием всех доступных команд
     * @throws IOException если произошла ошибка ввода/вывода
     */
    @Override
    public String execute(String[] args) throws IOException {
        final StringBuilder help = new StringBuilder("Доступные команды:\n");
        commands.forEach((name, command) -> 
                help.append(String.format("%s - %s\n", name, command.getDescription())));
        return help.toString();
    }
}
