package command;

import java.io.File;
import java.io.IOException;
import util.FileManager;

/**
 * Команда для сохранения коллекции в файл.
 */
public class SaveCommand extends AbstractCommand {

    private final String fileName;
    private final com.google.gson.Gson gson;
    private final FileManager fileManager;

    /**
     * Конструктор команды сохранения.
     *
     * @param fileName имя файла для сохранения
     * @param gson объект Gson для сериализации
     */
    public SaveCommand(String fileName, com.google.gson.Gson gson) {
        super("save", "сохранить коллекцию в файл", null);
        this.fileName = fileName;
        this.gson = gson;
        this.fileManager = new FileManager(gson);
    }

    /**
     * Выполняет команду сохранения коллекции в файл.
     *
     * @param args аргументы команды (не используются)
     * @return результат выполнения команды
     * @throws IOException если произошла ошибка при записи в файл
     */
    @Override
    public String execute(String[] args) throws IOException {
        if (collection == null) {
            return "Ошибка: коллекция не установлена";
        }
        final File savedFile = fileManager.saveCollection(fileName, collection.getCollection());
        return "Коллекция успешно сохранена в файл: " + savedFile.getAbsolutePath();
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
