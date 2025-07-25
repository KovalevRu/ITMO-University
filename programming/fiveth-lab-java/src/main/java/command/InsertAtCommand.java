package command;

import java.io.IOException;
import model.StudyGroup;
import util.StudyGroupInputHandler;

/**
 * Команда для вставки элемента в указанную позицию.
 */
public class InsertAtCommand extends AbstractCommand {

    /**
     * Конструктор команды вставки элемента.
     *
     * @param inputHandler обработчик ввода данных
     */
    public InsertAtCommand(StudyGroupInputHandler inputHandler) {
        super("insert_at", "добавить новый элемент в заданную позицию", inputHandler);
    }

    /**
     * Вставляет элемент в указанную позицию.
     * Если позиция больше размера коллекции, элемент добавляется в конец.
     * После вставки обновляет ID всех элементов справа от вставленного.
     *
     * @param args аргументы команды (должен быть указан индекс)
     * @return сообщение о результате выполнения команды
     * @throws IllegalArgumentException если индекс не указан или некорректен
     * @throws IOException если произошла ошибка при вводе данных
     */
    @Override
    public String execute(String[] args) throws IOException {
        if (collection == null) {
            return "Ошибка: коллекция не установлена";
        }
        if (inputHandler == null) {
            return "Ошибка: обработчик ввода не установлен";
        }
        
        if (args.length != 1) {
            throw new IllegalArgumentException("Необходимо указать индекс");
        }

        final int index;
        try {
            index = Integer.parseInt(args[0]);
            if (index < 0) {
                throw new IllegalArgumentException("Индекс должен быть неотрицательным числом");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Индекс должен быть числом");
        }

        final StudyGroup newGroup = inputHandler.readStudyGroup();
        final int collectionSize = collection.size();

        // Преобразуем ID в индекс (ID начинается с 1, индекс с 0)
        final int insertIndex = index - 1;

        if (insertIndex >= collectionSize) {
            newGroup.setId(collectionSize + 1); // Устанавливаем ID равным размеру коллекции + 1
            collection.add(newGroup);
            return "Запрошенная позиция " + index + " превышает размер коллекции "
                  +  "(" + collectionSize
                  + "). Элемент вставлен в конец коллекции (позиция " + (collectionSize + 1)
                  + ", индекс в массиве: " + collectionSize + ")";
        } else {
            // Сдвигаем все элементы вправо, начиная с конца
            for (int i = collectionSize - 1; i >= insertIndex; i--) {
                final StudyGroup group = collection.get(i);
                group.setId(group.getId() + 1);
            }
            // Вставляем новый элемент с правильным ID
            newGroup.setId(index); // ID = указанный индекс
            collection.insertAt(insertIndex, newGroup);
            return "Элемент вставлен в позицию " + index + " (индекс в "
                    + "массиве: " + insertIndex + ")";
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
