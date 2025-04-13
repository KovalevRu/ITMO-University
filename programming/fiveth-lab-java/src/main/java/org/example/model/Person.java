package org.example.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class Person implements Comparable<Person> {
    private static final Set<String> usedPassportIds = new HashSet<>();

    private String name; //Поле не может быть null, Строка не может быть пустой
    private Date birthday; //Поле может быть null
    private Float height; //Поле не может быть null, Значение поля должно быть больше 0
    private String passportID; //Значение этого поля должно быть уникальным, Поле не может быть null
    private Color eyeColor; //Поле может быть null

    public Person(String name, Date birthday, Float height, String passportID, Color eyeColor) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (height == null || height <= 0) {
            throw new IllegalArgumentException("Height must be greater than 0");
        }
        if (passportID == null) {
            throw new IllegalArgumentException("Passport ID cannot be null");
        }
        if (usedPassportIds.contains(passportID)) {
            throw new IllegalArgumentException("Passport ID must be unique");
        }

        this.name = name;
        this.birthday = birthday != null ? new Date(birthday.getTime()) : null;
        this.height = height;
        this.passportID = passportID;
        this.eyeColor = eyeColor;
        usedPassportIds.add(passportID);
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday != null ? new Date(birthday.getTime()) : null;
    }

    public Float getHeight() {
        return height;
    }

    public String getPassportID() {
        return passportID;
    }

    public Color getEyeColor() {
        return eyeColor;
    }


    public void releasePassportID() {
        usedPassportIds.remove(this.passportID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(birthday, person.birthday) &&
                Objects.equals(height, person.height) &&
                Objects.equals(passportID, person.passportID) &&
                eyeColor == person.eyeColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday, height, passportID, eyeColor);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", height=" + height +
                ", passportID='" + passportID + '\'' +
                ", eyeColor=" + eyeColor +
                '}';
    }

    @Override
    public int compareTo(Person other) {
        return this.name.compareTo(other.name);
    }
} 