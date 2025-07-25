package util;

import java.util.Date;
import java.util.Scanner;
import model.Color;
import model.Coordinates;
import model.FormOfEducation;
import model.Person;
import model.StudyGroup;

/**
 * Класс для интерактивного ввода данных StudyGroup.
 */
public class StudyGroupInputHandler {

    private final Scanner scanner;
    private final IdGenerator idGenerator;

    /**
     * Конструктор класса StudyGroupInputHandler.
     *
     * @param scanner     объект Scanner для чтения ввода
     * @param idGenerator генератор уникальных ID
     */
    public StudyGroupInputHandler(Scanner scanner, IdGenerator idGenerator) {
        this.scanner = scanner;
        this.idGenerator = idGenerator;
    }

    /**
     * Читает данные StudyGroup с консоли.
     *
     * @return новый объект StudyGroup с введенными данными
     */
    public StudyGroup readStudyGroup() {
        final StudyGroup group = new StudyGroup();
        group.setId(idGenerator.getNextId());

        System.out.print("Введите название группы: ");
        group.setName(readString());

        System.out.print("Введите координаты (Число):\n");
        group.setCoordinates(readCoordinates());

        System.out.print("Введите количество студентов (больше нуля): ");
        final int studentsCount = readPositiveInt();
        group.setStudentsCount(studentsCount);

        group.setShouldBeExpelled(readShouldBeExpelled(studentsCount));

        System.out.print("Введите среднюю оценку (больше нуля): ");
        group.setAverageMark(readPositiveFloat());

        System.out.print("Выберите форму обучения: ");
        group.setFormOfEducation(readFormOfEducation());

        System.out.print("Введите данные администратора группы (оставьте пустым для пропуска): ");
        group.setGroupAdmin(readPerson());

        return group;
    }

    /**
     * Читает координаты с консоли.
     *
     * @return новый объект Coordinates с введенными данными
     */
    private Coordinates readCoordinates() {
        final Coordinates coordinates = new Coordinates();
        System.out.print("Введите координату X: ");
        coordinates.setX1(readDouble());
        System.out.print("Введите координату Y: ");
        coordinates.setY1(readDouble());
        return coordinates;
    }

    /**
     * Читает данные Person с консоли.
     *
     * @return новый объект Person с введенными данными или null, если ввод пустой
     */
    private Person readPerson() {
        final String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return null;
        }

        final Person person = new Person();
        System.out.print("Введите им: ");
        person.setName(readString());

        System.out.print("Введите дату рождения (yyyy-MM-dd) или оставьте пустым: ");
        person.setBirthday(readDate());

        System.out.print("Введите рост: ");
        person.setHeight(readPositiveFloat());

        System.out.print("Введите номер паспорта: ");
        person.setPassportId(readString());

        System.out.print("Выберите цвет глаз: ");
        person.setEyeColor(readColor());

        return person;
    }

    /**
     * Читает непустую строку с консоли.
     *
     * @return введенная строка
     */
    private String readString() {
        String input;
        do {
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.print("Значение не может быть пустым. Повторите ввод: ");
            }
        } while (input.isEmpty());
        return input;
    }

    /**
     * Читает положительное целое число с консоли.
     *
     * @return введенное положительное целое число
     */
    private int readPositiveInt() {
        return readPositiveNumber("целое число", Integer::parseInt);
    }

    /**
     * Читает количество отчисляемых студентов с консоли.
     *
     * @param studentsCount общее количество студентов в группе
     * @return количество отчисляемых студентов
     */
    private long readShouldBeExpelled(int studentsCount) {
        long shouldBeExpelled;
        do {
            System.out.print("Введите количество отчисляемых (минимум 1): ");
            shouldBeExpelled = readPositiveLong();
            if (shouldBeExpelled > studentsCount) {
                System.out.println("Ошибка: Количество отчисляемых не может быть больше "
                        + "количества студентов в группе");
            }
        } while (shouldBeExpelled > studentsCount);
        return shouldBeExpelled;
    }

    /**
     * Читает положительное целое число типа long с консоли.
     *
     * @return введенное положительное целое число типа long
     */
    private long readPositiveLong() {
        while (true) {
            try {
                final long value = Long.parseLong(scanner.nextLine().trim());
                if (value < 0) {
                    throw new NumberFormatException();
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Введите положительное целое число и не ноль: ");
            }
        }
    }

    /**
     * Читает положительное число с плавающей точкой с консоли.
     *
     * @return введенное положительное число с плавающей точкой
     */
    private float readPositiveFloat() {
        return readPositiveNumber("число", Float::parseFloat);
    }

    /**
     * Читает положительное число заданного типа с консоли.
     *
     * @param typeName название типа числа для сообщений об ошибках
     * @param parser   функция для преобразования строки в число
     * @param <T>      тип числа
     * @return введенное положительное число
     */
    private <T extends Number> T readPositiveNumber(String typeName,
                                                    java.util.function.Function<String, T> parser) {
        while (true) {
            try {
                final T value = parser.apply(scanner.nextLine().trim());
                if (value.doubleValue() <= 0) {
                    throw new NumberFormatException();
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Введите положительное " + typeName + ": ");
            }
        }
    }

    /**
     * Читает число с плавающей точкой с консоли.
     *
     * @return введенное число с плавающей точкой
     */
    private double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Введите число: ");
            }
        }
    }

    /**
     * Читает форму обучения с консоли.
     *
     * @return выбранная форма обучения
     */
    private FormOfEducation readFormOfEducation() {
        while (true) {
            System.out.println("Выберите форму обучения:");
            final FormOfEducation[] forms = FormOfEducation.values();
            for (int i = 0; i < forms.length; i++) {
                System.out.println((i + 1) + ". " + forms[i].name());
            }
            System.out.print("Введите номер (1-" + forms.length + ") или название формы обуч.: ");

            final String input = scanner.nextLine().trim();

            // Проверка на пустой ввод
            if (input.isEmpty()) {
                System.out.println("Ошибка: Ввод не может быть пустым");
                continue;
            }

            // TODO сделать проверку через регулярки.
            // Проверка на номер через регулярное выражение
            if (input.matches("^[1-9]\\d*$")) {
                final int index = Integer.parseInt(input) - 1;
                if (index < forms.length) {
                    return forms[index];
                }
                System.out.println("Ошибка: Номер должен быть от 1 до " + forms.length);
                continue;
            }

            // Проверка на название формы обучения через регулярное выражение
            // Разрешаем ввод в любом регистре и с подчеркиваниями или пробелами
            final String normalizedInput = input.toUpperCase().replaceAll("[\\s_]+", "_");
            for (FormOfEducation form : forms) {
                if (form.name().equals(normalizedInput)) {
                    return form;
                }
            }

            System.out.println("Ошибка: Неверный формат ввода. "
                    + "Пожалуйста, введите номер (1-" + forms.length + ")"
                    + " или название формы обучения");
        }
    }

    /**
     * Читает цвет глаз с консоли.
     *
     * @return выбранный цвет глаз
     */
    private Color readColor() {
        while (true) {
            System.out.println("Выберите цвет глаз:");
            final Color[] colors = Color.values();
            for (int i = 0; i < colors.length; i++) {
                System.out.println((i + 1) + ". " + colors[i].name());
            }
            System.out.print("Введите номер (1-" + colors.length + ") или название цвета: ");
            
            final String input = scanner.nextLine().trim();
            
            // Проверка на пустой ввод
            if (input.isEmpty()) {
                System.out.println("Ошибка: Ввод не может быть пустым");
                continue;
            }

            // Проверка на номер через регулярное выражение
            if (input.matches("^[1-9]\\d*$")) {
                final int userChoice = Integer.parseInt(input);
                if (userChoice >= 1 && userChoice <= colors.length) {
                    return colors[userChoice - 1];
                }
                System.out.println("Ошибка: Номер должен быть от 1 до " + colors.length);
                continue;
            }

            // Проверка на название цвета через регулярное выражение
            // Разрешаем ввод в любом регистре и с подчеркиваниями или пробелами
            final String normalizedInput = input.toUpperCase().replaceAll("[\\s_]+", "_");
            for (Color color : colors) {
                if (color.name().equals(normalizedInput)) {
                    return color;
                }
            }

            System.out.println("Ошибка: Неверный формат ввода. "
                    + "Пожалуйста, введите номер (1-" + colors.length + ") или название цвета");
        }
    }

    /**
     * Читает дату с консоли.
     *
     * @return введенная дата или null, если ввод пустой
     */
    private Date readDate() {
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return null;
        }
        while (true) {
            try {
                return java.sql.Date.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.print("Ошибка: Неверный формат даты. Введите дату в формате yyyy-MM-dd"
                        + " или оставьте пустым: ");
                input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    return null;
                }
            }
        }
    }
} 

