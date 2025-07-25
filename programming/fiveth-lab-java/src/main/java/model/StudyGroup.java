package model;

import java.time.LocalDateTime;

/**
 * Класс учебной группы.
 */
public class StudyGroup implements Comparable<StudyGroup> {

    private long id;
    private String name;
    private Coordinates coordinates;
    private LocalDateTime creationDate;
    private int studentsCount;
    private long shouldBeExpelled;
    private Float averageMark;
    private FormOfEducation formOfEducation;
    private Person groupAdmin;

    /**
     * Конструктор класса StudyGroup.
     * Инициализирует дату создания текущим временем.
     */
    public StudyGroup() {
        this.creationDate = LocalDateTime.now();
    }

    /**
     * Возвращает ID учебной группы.
     *
     * @return ID учебной группы
     */
    public long getId() {
        return id;
    }

    /**
     * Устанавливает ID учебной группы.
     *
     * @param id новый ID
     * @throws IllegalArgumentException если ID меньше или равен нулю
     */
    public void setId(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID должен быть больше 0");
        }
        this.id = id;
    }

    /**
     * Возвращает название учебной группы.
     *
     * @return название учебной группы
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает название учебной группы.
     *
     * @param name новое название
     * @throws IllegalArgumentException если название равно null или пустое
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть null или пустым");
        }
        this.name = name;
    }

    /**
     * Возвращает координаты учебной группы.
     *
     * @return координаты учебной группы
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Устанавливает координаты учебной группы.
     *
     * @param coordinates новые координаты
     * @throws IllegalArgumentException если координаты равны null
     */
    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Координаты не могут быть null");
        }
        this.coordinates = coordinates;
    }

    /**
     * Возвращает дату создания учебной группы.
     *
     * @return дата создания
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Возвращает количество студентов в группе.
     *
     * @return количество студентов
     */
    public int getStudentsCount() {
        return studentsCount;
    }

    /**
     * Устанавливает количество студентов в группе.
     *
     * @param studentsCount новое количество студентов
     * @throws IllegalArgumentException если количество студентов меньше или равно нулю
     */
    public void setStudentsCount(int studentsCount) {
        if (studentsCount <= 0) {
            throw new IllegalArgumentException("Кол-во студентов строго больше 0");
        }
        this.studentsCount = studentsCount;
    }

    /**
     * Возвращает количество студентов, подлежащих отчислению.
     *
     * @return количество отчисляемых студентов
     */
    public long getShouldBeExpelled() {
        return shouldBeExpelled;
    }

    /**
     * Устанавливает количество студентов, подлежащих отчислению.
     *
     * @param shouldBeExpelled новое количество отчисляемых студентов
     * @throws IllegalArgumentException если количество отчисляемых меньше или равно нулю
     */
    public void setShouldBeExpelled(long shouldBeExpelled) {
        if (shouldBeExpelled <= 0) {
            throw new IllegalArgumentException("Отчисляемых строго больше 0");
        }
        this.shouldBeExpelled = shouldBeExpelled;
    }

    /**
     * Возвращает среднюю оценку в группе.
     *
     * @return средняя оценка
     */
    public Float getAverageMark() {
        return averageMark;
    }

    /**
     * Устанавливает среднюю оценку в группе.
     *
     * @param averageMark новая средняя оценка
     * @throws IllegalArgumentException если средняя оценка меньше или равна нулю или равна null
     */
    public void setAverageMark(Float averageMark) {
        if (averageMark == null || averageMark <= 0) {
            throw new IllegalArgumentException(
                    "Средняя оценка должна быть больше 0 и не может быть null");
        }
        this.averageMark = averageMark;
    }

    /**
     * Возвращает форму обучения.
     *
     * @return форма обучения
     */
    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    /**
     * Устанавливает форму обучения.
     *
     * @param formOfEducation новая форма обучения
     * @throws IllegalArgumentException если форма обучения равна null
     */
    public void setFormOfEducation(FormOfEducation formOfEducation) {
        if (formOfEducation == null) {
            throw new IllegalArgumentException(
                    "Форма обучения не может быть null");
        }
        this.formOfEducation = formOfEducation;
    }

    /**
     * Возвращает администратора группы.
     *
     * @return администратор группы
     */
    public Person getGroupAdmin() {
        return groupAdmin;
    }

    /**
     * Устанавливает администратора группы.
     *
     * @param groupAdmin новый администратор группы
     */
    public void setGroupAdmin(Person groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    /**
     * Метод для сравнения.
     *
     * @param other объект для сравнения
     * @return отрицательное число, если this меньше other, 0 если равны,
     *     положительное число если this больше other
     */
    @Override
    public int compareTo(StudyGroup other) {
        // Сначала сравниваем по studentsCount
        int result = Integer.compare(this.studentsCount, other.studentsCount);
        if (result != 0) {
            return result;
        }

        // Затем по shouldBeExpelled
        result = Long.compare(this.shouldBeExpelled, other.shouldBeExpelled);
        if (result != 0) {
            return result;
        }

        // Затем по name
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "StudyGroup{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", coordinates=" + coordinates
                + ", creationDate=" + creationDate
                + ", studentsCount=" + studentsCount
                + ", shouldBeExpelled=" + shouldBeExpelled
                + ", averageMark=" + averageMark
                + ", formOfEducation=" + formOfEducation
                + ", groupAdmin=" + groupAdmin
                + '}';
    }
}
