package model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * Класс инкапсулирующий вектор учебных групп.
 */
public class StudyGroupCollection {

    private final Vector<StudyGroup> collection;
    private final LocalDateTime initializationDate;

    /**
     * Конструктор класса StudyGroupCollection.
     * Создает пустую коллекцию и устанавливает дату инициализации.
     */
    public StudyGroupCollection() {
        this.collection = new Vector<>();
        this.initializationDate = LocalDateTime.now();
    }

    /**
     * Добавляет учебную группу в коллекцию.
     *
     * @param group учебная группа для добавления
     */
    public void add(StudyGroup group) {
        collection.add(group);
    }

    /**
     * Удаляет учебную группу по ID.
     *
     * @param id ID группы для удаления
     * @return true, если группа была удалена, false в противном случае
     */
    public boolean removeById(long id) {
        return collection.removeIf(group -> group.getId() == id);
    }

    /**
     * Обновляет учебную группу по ID.
     *
     * @param id ID группы для обновления
     * @param newGroup новая версия группы
     * @return true, если группа была обновлена, false в противном случае
     */
    public boolean update(long id, StudyGroup newGroup) {
        for (int i = 0; i < collection.size(); i++) {
            if (collection.get(i).getId() == id) {
                collection.set(i, newGroup);
                return true;
            }
        }
        return false;
    }

    /**
     * Очищает коллекцию.
     */
    public void clear() {
        collection.clear();
    }

    /**
     * Возвращает информацию о коллекции.
     *
     * @return строка с информацией о типе коллекции, дате инициализации и количестве элементов
     */
    public String getCollectionInfo() {
        return "Тип коллекции: Vector\n"
                + "Дата инициализации: " + initializationDate + "\n"
                + "Количество элементов: " + collection.size();
    }

    /**
     * Возвращает строковое представление всех элементов коллекции.
     *
     * @return строка с представлением всех элементов или сообщение о пустой коллекции
     */
    public String getCollectionAsString() {
        if (collection.isEmpty()) {
            return "Коллекция пуста";
        }
        return collection.stream()
                .map(StudyGroup::toString)
                .collect(Collectors.joining("\n"));
    }

    /**
     * Возвращает первый элемент коллекции.
     *
     * @return первый элемент или null, если коллекция пуста
     */
    public StudyGroup getFirst() {
        return collection.isEmpty() ? null : collection.firstElement();
    }

    /**
     * Подсчитывает количество групп с заданным количеством студентов.
     *
     * @param studentsCount количество студентов для поиска
     * @return количество найденных групп
     */
    public int countByStudentsCount(long studentsCount) {
        return (int) collection.stream()
                .filter(group -> group.getStudentsCount() == studentsCount)
                .count();
    }

    /**
     * Возвращает отсортированный список строковых представлений администраторов групп.
     *
     * @return строка с отсортированными представлениями администраторов
     */
    public String getGroupAdminFieldAscending() {
        return collection.stream()
                .map(group -> group.getGroupAdmin().toString())
                .sorted()
                .collect(Collectors.joining("\n"));
    }

    /**
     * Находит группу с минимальным значением.
     *
     * @return минимальная группа или null, если коллекция пуста
     */
    public StudyGroup findMin() {
        return collection.stream()
                .min(StudyGroup::compareTo)
                .orElse(null);
    }

    /**
     * Находит группу по ID.
     *
     * @param id ID группы для поиска
     * @return найденная группа или null, если группа не найдена
     */
    public StudyGroup findById(long id) {
        return collection.stream()
                .filter(group -> group.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Обновляет группу по индексу.
     *
     * @param index индекс группы для обновления
     * @param group новая группа
     */
    public void set(int index, StudyGroup group) {
        if (index >= 0 && index < collection.size()) {
            collection.set(index, group);
        }
    }

    /**
     * Возвращает индекс группы.
     *
     * @param group группа для поиска индекса
     * @return индекс группы или -1, если группа не найдена
     */
    public int indexOf(StudyGroup group) {
        return collection.indexOf(group);
    }

    /**
     * Вставляет группу по индексу.
     *
     * @param index индекс для вставки
     * @param group группа для вставки
     */
    public void insertAt(int index, StudyGroup group) {
        if (index >= collection.size()) {
            collection.add(group);
        } else {
            collection.insertElementAt(group, index);
        }
    }

    /**
     * Возвращает размер коллекции.
     *
     * @return размер коллекции
     */
    public int size() {
        return collection.size();
    }

    /**
     * Возвращает группу по индексу.
     *
     * @param index индекс группы
     * @return группа по указанному индексу или null, если индекс вне диапазона
     */
    public StudyGroup get(int index) {
        return (index >= 0 && index < collection.size()) ? collection.get(index) : null;
    }

    /**
     * Группирует элементы по администратору группы и возвращает кол-во элементов в каждой группе.
     *
     * @return map, где ключ - имя администратора, значение - кол-во групп с этим администратором
     */
    public Map<String, Long> groupCountingByGroupAdmin() {
        return collection.stream()
                .filter(group -> group.getGroupAdmin() != null)
                .collect(Collectors.groupingBy(
                        group -> group.getGroupAdmin().getName(),
                        Collectors.counting()));
    }

    /**
     * Возвращает максимальный ID в коллекции.
     *
     * @return максимальный ID или 0, если коллекция пуста
     */
    public long getMaxId() {
        return collection.stream()
                .mapToLong(StudyGroup::getId)
                .max()
                .orElse(0);
    }

    /**
     * Возвращает коллекцию учебных групп.
     * Этот метод должен использоваться только для сохранения коллекции.
     *
     * @return вектор с учебными группами
     */
    public Vector<StudyGroup> getCollection() {
        return collection;
    }

    /**
     * Устанавливает новую коллекцию учебных групп.
     *
     * @param newCollection новая коллекция для установки
     */
    private void setCollection(Vector<StudyGroup> newCollection) {
        collection.clear();
        collection.addAll(newCollection);
    }

    /**
     * Загружает данные из внешнего вектора в коллекцию.
     *
     * @param loadedCollection загруженная коллекция
     * @throws IllegalArgumentException если в коллекции найдены дублирующиеся ID
     */
    public void loadFromVector(Vector<StudyGroup> loadedCollection) {
        // Проверяем уникальность ID
        final Set<Long> ids = new HashSet<>();
        for (StudyGroup group : loadedCollection) {
            if (!ids.add(group.getId())) {
                throw new IllegalArgumentException(
                    "Ошибка загрузки: найден дублирующийся ID " + group.getId());
            }
        }
        setCollection(loadedCollection);
    }
}
