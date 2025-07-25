package util;

import com.google.gson.Gson;
import command.AddCommand;
import command.AddIfMinCommand;
import command.ClearCommand;
import command.CollectionCommand;
import command.Command;
import command.CountByStudentsCountCommand;
import command.ExecuteScriptCommand;
import command.ExitCommand;
import command.GroupCountingByGroupAdminCommand;
import command.HelpCommand;
import command.InfoCommand;
import command.InsertAtCommand;
import command.PrintFieldAscendingGroupAdminCommand;
import command.RemoveByIdCommand;
import command.RemoveFirstCommand;
import command.SaveCommand;
import command.ShowCommand;
import command.UpdateCommand;
import command.WelcomeCommand;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import model.StudyGroupCollection;

/**
 * Класс для управления командами.
 */
public class CommandManager {

    private final Map<String, Command> commands;
    private final StudyGroupCollection collection;
    private final FileManager fileManager;

    /**
     * Конструктор класса CommandManager.
     *
     * @param collection коллекция StudyGroup
     * @param fileName имя файла для сохранения/загрузки
     * @param gson объект Gson для сериализации/десериализации
     * @param inputHandler обработчик ввода данных
     */
    public CommandManager(StudyGroupCollection collection, String fileName,
            Gson gson, StudyGroupInputHandler inputHandler) {
        this.collection = collection;
        this.fileManager = new FileManager(gson);
        this.commands = initializeCommands(fileName, gson, inputHandler);
    }

    /**
     * Инициализирует карту доступных команд.
     *
     * @param fileName имя файла для сохранения/загрузки
     * @param gson объект Gson для сериализации/десериализации
     * @param inputHandler обработчик ввода данных
     * @return карта команд, где ключ - имя команды, значение - объект команды
     */
    private Map<String, Command> initializeCommands(String fileName, Gson gson,
            StudyGroupInputHandler inputHandler) {
        final Map<String, Command> commands = new HashMap<>();

        // Простые команды, не требующие коллекцию
        commands.put("welcome", new WelcomeCommand());
        commands.put("help", new HelpCommand(commands));
        commands.put("exit", new ExitCommand());

        // Команды, работающие с коллекцией
        addCollectionCommand(commands, "info", new InfoCommand());
        addCollectionCommand(commands, "show", new ShowCommand());
        addCollectionCommand(commands, "clear", new ClearCommand());
        addCollectionCommand(commands, "save", new SaveCommand(fileName, gson));
        addCollectionCommand(commands, "remove_by_id", new RemoveByIdCommand());
        addCollectionCommand(commands, "remove_first", new RemoveFirstCommand());
        addCollectionCommand(commands, "count_by_students_count", 
            new CountByStudentsCountCommand());
        addCollectionCommand(commands, "group_counting_by_group_admin", 
            new GroupCountingByGroupAdminCommand());
        addCollectionCommand(commands, "print_field_ascending_group_admin", 
            new PrintFieldAscendingGroupAdminCommand());

        // Команды, требующие inputHandler
        if (inputHandler != null) {
            addCollectionCommand(commands, "add", new AddCommand(inputHandler));
            addCollectionCommand(commands, "update", new UpdateCommand(inputHandler));
            addCollectionCommand(commands, "insert_at", new InsertAtCommand(inputHandler));
            addCollectionCommand(commands, "add_if_min", new AddIfMinCommand(inputHandler));
        }

        // Особая команда для выполнения скриптов
        addCollectionCommand(commands, "execute_script", 
            new ExecuteScriptCommand(commands, fileManager));

        return commands;
    }

    /**
     * Добавляет команду, работающую с коллекцией, в карту команд.
     *
     * @param commands карта команд
     * @param name имя команды
     * @param command команда
     */
    private void addCollectionCommand(Map<String, Command> commands, 
        String name, CollectionCommand command) {
        command.setCollection(collection);
        commands.put(name, command);
    }

    /**
     * Выполняет команду с заданными аргументами.
     *
     * @param commandName имя команды
     * @param args аргументы команды
     * @return результат выполнения команды
     */
    public String executeCommand(String commandName, String[] args) {
        final Command command = commands.get(commandName);
        if (command == null) {
            return "Неизвестная команда. Введите 'help' для списка доступных команд.";
        }

        try {
            return command.execute(args);
        } catch (IllegalArgumentException e) {
            return "Ошибка: " + e.getMessage();
        } catch (IOException e) {
            return "Ошибка при выполнении команды: " + e.getMessage();
        }
    }
}
