package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import model.StudyGroup;

/**
 * Класс для управления файлами.
 */
public class FileManager {

    private final Gson gson;

    /**
     * Конструктор класса FileManager.
     *
     * @param gson объект Gson для сериализации/десериализации
     */
    public FileManager(Gson gson) {
        this.gson = gson;
    }

    /**
     * Сохраняет коллекцию в файл.
     *
     * @param fileName имя файла для сохранения
     * @param collection коллекция для сохранения
     * @return созданный файл
     * @throws IOException если произошла ошибка при записи в файл
     */
    public File saveCollection(String fileName, Vector<StudyGroup> collection) throws IOException {
        final File file = new File(fileName);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
            final byte[] data = gson.toJson(collection).getBytes(StandardCharsets.UTF_8);
            bos.write(data);
            bos.flush();
        }
        
        return file;
    }

    /**
     * Загружает коллекцию из файла.
     * Если файл не существует, создает новый пустой файл.
     *
     * @param fileName имя файла для загрузки
     * @return загруженная коллекция или пустая коллекция, если файл пуст
     * @throws IOException если произошла ошибка при чтении файла
     */
    public Vector<StudyGroup> loadCollection(String fileName) throws IOException {
        final File file = new File(fileName);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            System.out.println("Создан новый файл: " + fileName);
            return new Vector<>();
        }

        try (FileReader reader = new FileReader(file)) {
            final TypeToken<Vector<StudyGroup>> token = new TypeToken<>() {
            };
            try {
                final Vector<StudyGroup> loadedCollection = gson.fromJson(reader, token.getType());
                return loadedCollection != null ? loadedCollection : new Vector<>();
            } catch (com.google.gson.JsonSyntaxException e) {
                throw new IOException("Ошибка парсинга JSON файла: файл поврежден "
                        + "или имеет неверный формат", e);
            }
        }
    }
    
    /**
     * Читает скрипт из файла.
     *
     * @param fileName имя файла скрипта
     * @return список строк скрипта, исключая пустые строки и комментарии
     * @throws IOException если произошла ошибка при чтении файла
     */
    public List<String> readScript(String fileName) throws IOException {
        final File file = new File(fileName);
        if (!file.exists()) {
            throw new IOException("Файл '" + fileName + "' не найден");
        }
        
        final List<String> scriptLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty() && !line.startsWith("#")) {
                    scriptLines.add(line);
                }
            }
        }
        return scriptLines;
    }
}
