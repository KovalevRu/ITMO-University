package command;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import util.FileManager;

/**
 * Команда для выполнения скрипта из файла.
 */
public class ExecuteScriptCommand extends AbstractCommand {

    private static final Stack<String> scriptStack = new Stack<>();
    private final Map<String, Command> commands;
    private final FileManager fileManager;

    /**
     * Конструктор команды выполнения скрипта.
     *
     * @param commands карта доступных команд
     * @param fileManager менеджер файлов для чтения скрипта
     */
    public ExecuteScriptCommand(Map<String, Command> commands, FileManager fileManager) {
        super("execute_script", "считать и исполнить скрипт из указанного файла", null);
        this.commands = commands;
        this.fileManager = fileManager;
    }

    /**
     * Выполняет скрипт из указанного файла.
     * Читает команды из файла построчно и выполняет их.
     * Пропускает пустые строки и строки, начинающиеся с #.
     * Запрещает циклические вызовы скриптов.
     *
     * @param args аргументы команды (должно быть указано имя файла скрипта)
     * @return результат выполнения всех команд из скрипта
     * @throws IOException если произошла ошибка при чтении файла
     */
    @Override
    public String execute(String[] args) throws IOException {
        if (collection == null) {
            return "Ошибка: коллекция не установлена";
        }
        
        if (args.length != 1) {
            throw new IllegalArgumentException("Необходимо указать имя файла скрипта");
        }

        final String fileName = args[0];
        
        // Проверяем, не создаст ли этот скрипт циклический вызов
        if (scriptStack.contains(fileName)) {
            return "Обнаружен циклический вызов скриптов. Не делайте гадости такие.";
        }

        try {
            scriptStack.push(fileName);
            final List<String> scriptLines = fileManager.readScript(fileName);
            final StringBuilder result = new StringBuilder();

            for (String line : scriptLines) {
                final String[] parts = line.split("\\s+", 2);
                final String commandName = parts[0].toLowerCase();
                final String[] commandArgs = parts.length > 1 
                        ? parts[1].split("\\s+") 
                        : new String[0];

                final Command command = commands.get(commandName);
                if (command == null) {
                    result.append("Неизвестная команда: ")
                            .append(commandName)
                            .append("\n");
                    continue;
                }

                try {
                    // Если команда работает с коллекцией, установим ее
                    if (command instanceof CollectionCommand) {
                        ((CollectionCommand) command).setCollection(collection);
                    }
                    
                    result.append(command.execute(commandArgs))
                            .append("\n");
                } catch (IOException | IllegalArgumentException e) {
                    result.append("Ошибка при выполнении команды ")
                            .append(commandName)
                            .append(": ")
                            .append(e.getMessage())
                            .append("\n");
                }
            }
            return result.toString();
        } catch (IOException e) {
            return "Ошибка при чтении файла '" + fileName + "': " + e.getMessage();
        } finally {
            scriptStack.pop();
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
