package models;

import utility.Validatable;

public class Person implements Validatable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.util.Date birthday; //Поле может быть null
    private Float height; //Поле не может быть null, Значение поля должно быть больше 0
    private String passportID; //Значение этого поля должно быть уникальным, Поле не может быть null
    private Color eyeColor; //Поле может быть null

    @Override
    public boolean isValid() {
        if (name == null || name.isEmpty()) return false;
        if (birthday == null) return false;
        if (height == null || height <= 0) return false;
        if (passportID == null) return false;
        if (eyeColor == null) return false;
            return true;
    }
}
