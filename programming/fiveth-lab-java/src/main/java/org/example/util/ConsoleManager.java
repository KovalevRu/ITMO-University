package org.example.util;

import org.example.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class ConsoleManager {
    private final Scanner scanner;
    private final SimpleDateFormat dateFormat;

    public ConsoleManager() {
        this.scanner = new Scanner(System.in);
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    public String readString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }

    public String readNonEmptyString(String prompt) {
        while (true) {
            String input = readString(prompt);
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Значение не может быть пустым. Пожалуйста, попробуйте снова.");
        }
    }

    public Float readFloat(String prompt) {
        while (true) {
            try {
                String input = readString(prompt);
                if (input.isEmpty()) {
                    return null;
                }
                return Float.parseFloat(input);
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат числа. Пожалуйста, попробуйте снова.");
            }
        }
    }

    public Float readPositiveFloat(String prompt) {
        while (true) {
            Float value = readFloat(prompt);
            if (value == null || value > 0) {
                return value;
            }
            System.out.println("Значение должно быть больше 0. Пожалуйста, попробуйте снова.");
        }
    }

    public Double readDouble(String prompt) {
        while (true) {
            try {
                String input = readString(prompt);
                if (input.isEmpty()) {
                    return null;
                }
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат числа. Пожалуйста, попробуйте снова.");
            }
        }
    }

    public Integer readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readNonEmptyString(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат числа. Пожалуйста, попробуйте снова.");
            }
        }
    }

    public Integer readPositiveInt(String prompt) {
        while (true) {
            Integer value = readInt(prompt);
            if (value > 0) {
                return value;
            }
            System.out.println("Значение должно быть больше 0. Пожалуйста, попробуйте снова.");
        }
    }

    public Long readLong(String prompt) {
        while (true) {
            try {
                return Long.parseLong(readNonEmptyString(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат числа. Пожалуйста, попробуйте снова.");
            }
        }
    }

    public Long readPositiveLong(String prompt) {
        while (true) {
            Long value = readLong(prompt);
            if (value > 0) {
                return value;
            }
            System.out.println("Значение должно быть больше 0. Пожалуйста, попробуйте снова.");
        }
    }

    public Date readDate(String prompt) {
        while (true) {
            try {
                String input = readString(prompt + " (дд/мм/гггг или пустая строка для null)");
                if (input.isEmpty()) {
                    return null;
                }
                return dateFormat.parse(input);
            } catch (ParseException e) {
                System.out.println("Неверный формат даты. Используйте формат дд/мм/гггг.");
            }
        }
    }

    public FormOfEducation readFormOfEducation(String prompt) {
        while (true) {
            System.out.println("Доступные формы обучения:");
            for (FormOfEducation form : FormOfEducation.values()) {
                System.out.println("- " + form);
            }
            String input = readNonEmptyString(prompt);
            try {
                return FormOfEducation.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Неверная форма обучения. Пожалуйста, попробуйте снова.");
            }
        }
    }

    public Color readColor(String prompt) {
        while (true) {
            System.out.println("Доступные цвета (или пустая строка для null):");
            for (Color color : Color.values()) {
                System.out.println("- " + color);
            }
            String input = readString(prompt);
            if (input.isEmpty()) {
                return null;
            }
            try {
                return Color.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Неверный цвет. Пожалуйста, попробуйте снова.");
            }
        }
    }

    public Coordinates readCoordinates() {
        Double x = readDouble("Введите координату X (Double)");
        double y = readDouble("Введите координату Y (double)");
        return new Coordinates(x, y);
    }

    public Person readPerson() {
        System.out.println("Введите данные администратора группы (или пустое имя для null):");
        String name = readString("Введите имя");
        if (name.isEmpty()) {
            return null;
        }
        Date birthday = readDate("Введите дату рождения");
        Float height = readPositiveFloat("Введите рост (должен быть больше 0)");
        String passportID = readNonEmptyString("Введите номер паспорта");
        Color eyeColor = readColor("Введите цвет глаз");
        return new Person(name, birthday, height, passportID, eyeColor);
    }

    public StudyGroup readStudyGroup() {
        String name = readNonEmptyString("Введите название группы");
        Coordinates coordinates = readCoordinates();
        int studentsCount = readPositiveInt("Введите количество студентов (должно быть больше 0)");
        long shouldBeExpelled = readPositiveLong("Введите количество студентов на отчисление (должно быть больше 0)");
        Float averageMark = readPositiveFloat("Введите средний балл (должен быть больше 0)");
        FormOfEducation formOfEducation = readFormOfEducation("Введите форму обучения");
        Person groupAdmin = readPerson();

        return new StudyGroup(name, coordinates, studentsCount, shouldBeExpelled,
                averageMark, formOfEducation, groupAdmin);
    }
} 