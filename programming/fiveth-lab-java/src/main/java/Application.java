package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import command.SaveCommand;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Vector;
import model.StudyGroup;
import model.StudyGroupCollection;
import util.CommandManager;
import util.FileManager;
import util.IdGenerator;
import util.LocalDateTimeAdapter;
import util.StudyGroupInputHandler;

/**
 * Главный класс приложения, управляющий коллекциеей и выполнением команд.
 */
public class Application {

    private final StudyGroupCollection collection;
    private final String fileName;
    private final Gson gson;
    private StudyGroupInputHandler inputHandler;
    private final Scanner scanner;
    private IdGenerator idGenerator;
    private final FileManager fileManager;
    private final CommandManager commandManager;
    private boolean shouldSaveOnExit = true;

    /**
     * Конструктор класса Application.
     * Инициализирует все необходимые компоненты приложения.
     *
     * @param fileName имя файла для сохранения/загрузки коллекции
     */
    public Application(String fileName) {
        this.collection = new StudyGroupCollection();
        this.fileName = fileName;
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        this.scanner = new Scanner(System.in);
        this.idGenerator = new IdGenerator(collection);
        this.inputHandler = new StudyGroupInputHandler(scanner, idGenerator);
        this.fileManager = new FileManager(gson);
        this.commandManager = new CommandManager(collection, fileName, gson, inputHandler);

        setupShutdownHook();
        loadCollection();
    }

    /**
     * Настраивает обработчик завершения работы приложения.
     * При получении сигнала завершения сохраняет коллекцию в файл.
     */
    private void setupShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (shouldSaveOnExit) {
                System.out.println("\nПолучен сигнал завершения. Сохраняем коллекцию...");
                try {
                    final SaveCommand saveCommand = new SaveCommand(fileName, gson);
                    saveCommand.setCollection(collection);
                    final String result = saveCommand.execute(new String[0]);
                    System.out.println(result);
                } catch (IOException e) {
                    System.err.println("Ошибка при сохранении: " + e.getMessage());
                }
            }
        }));
    }

    /**
     * Загружает коллекцию из файла.
     * Если файл не существует или пуст, создает новую пустую коллекцию.
     */
    private void loadCollection() {
        try {
            final Vector<StudyGroup> loadedCollection = fileManager.loadCollection(fileName);
            if (!loadedCollection.isEmpty()) {
                try {
                    collection.loadFromVector(loadedCollection);
                    this.idGenerator = new IdGenerator(collection);
                    this.inputHandler = new StudyGroupInputHandler(scanner, idGenerator);
                } catch (IllegalArgumentException e) {
                    System.err.println("Ошибка при загрузке коллекции: " + e.getMessage());
                    System.err.println("В файле обнаружены дублирующиеся ID.");
                    System.err.println("Хотите создать новую пустую коллекцию? (да/нет)");
                    
                    while (true) {
                        final String response = scanner.nextLine().trim().toLowerCase();
                        if (response.equals("да")) {
                            System.out.println("Создана новая пустая коллекция.");
                            break;
                        } else if (response.equals("нет")) {
                            System.err.println("Программа завершена. "
                                   + "Исправьте файл и попробуйте снова.");
                            shouldSaveOnExit = false;
                            System.exit(0);
                        } else {
                            System.err.println("Пожалуйста, введите Да или Нет");
                        }
                    } 
                }
            }
        } catch (IOException e) {
            if (e.getMessage().contains("Ошибка парсинга JSON файла")) {
                System.err.println("Ошибка при загрузке коллекции: " + e.getMessage());
                System.err.println("Файл поврежден или имеет неверный формат.");
                System.err.println("Хотите создать новую пустую коллекцию? (да/нет)");
                
                while (true) {
                    final String response = scanner.nextLine().trim().toLowerCase();
                    if (response.equals("да")) {
                        System.out.println("Создана новая пустая коллекция.");
                        break;
                    } else if (response.equals("нет")) {
                        System.err.println("Программа завершена. "
                              +  "Исправьте файл и попробуйте снова.");
                        shouldSaveOnExit = false;
                        System.exit(0);
                    } else {
                        System.err.println("Пожалуйста, введите Да или Нет");
                    }
                }
            } else {
                System.out.println("Ошибка чтения/создания файла: " + e.getMessage());
            }
        }
    }

    /**
     * Выполняет команду, введенную пользователем.
     *
     * @param input строка с командой и аргументами
     */
    public void executeCommand(String input) {
        final String[] parts = input.trim().split("\\s+", 2);
        final String commandName = parts[0].toLowerCase();
        final String[] args = parts.length > 1 ? parts[1].split("\\s+") : new String[0];

        final String result = commandManager.executeCommand(commandName, args);
        System.out.println(result);
    }

    /**
     * Точка входа в приложение.
     * Проверяет наличие переменной окружения с именем файла,
     * инициализирует приложение и запускает основной цикл обработки команд.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        final String fileName = System.getenv("LAB5_STUDY_GROUPS_FILE");
        if (fileName == null) {
            System.out.println("Переменная окружения LAB5_STUDY_GROUPS_FILE не установлена");
            return;
        }

        final Application app = new Application(fileName);

        // Выводим приветствие
        System.out.println(app.commandManager.executeCommand("welcome", new String[0]));

        while (true) {
            // Выводим приглашение к вводу
            System.out.print("> ");
            final String input;
            try {
                input = app.scanner.nextLine().trim();
            } catch (java.util.NoSuchElementException e) {
                // Обработка Ctrl+D
                System.out.println("\nПолучен сигнал EOF (Ctrl+D). Завершаем работу...");
                break;
            }
            
            if (input.equals("exit")) {
                break;
            }

            app.executeCommand(input);
        }

        app.scanner.close();
    }
} 
