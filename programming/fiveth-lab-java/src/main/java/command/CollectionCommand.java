package command;

import model.StudyGroupCollection;

/**
 * Интерфейс для команд, работающих с коллекцией.
 */
public interface CollectionCommand extends Command {
    
    /**
     * Устанавливает коллекцию для команды.
     *
     * @param collection коллекция для выполнения команды
     */
    void setCollection(StudyGroupCollection collection);
} 
