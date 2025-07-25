package util;

import model.StudyGroupCollection;

/**
 * Класс для генерации уникальных ID.
 */
public class IdGenerator {

    private long nextId;

    /**
     * Конструктор класса IdGenerator.
     * Инициализирует генератор ID на основе существующей коллекции.
     *
     * @param collection коллекция StudyGroup, на основе которой будет определен следующий ID
     */
    public IdGenerator(StudyGroupCollection collection) {
        this.nextId = collection.getMaxId() + 1;
    }

    /**
     * Возвращает следующий доступный ID и увеличивает счетчик.
     *
     * @return следующий уникальный ID
     */
    public long getNextId() {
        return nextId++;
    }
}
