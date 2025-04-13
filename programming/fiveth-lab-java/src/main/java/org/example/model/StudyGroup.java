package org.example.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;


public class StudyGroup implements Comparable<StudyGroup> {
    private static final AtomicLong ID_GENERATOR = new AtomicLong(1);

    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int studentsCount; //Значение поля должно быть больше 0
    private long shouldBeExpelled; //Значение поля должно быть больше 0
    private Float averageMark; //Значение поля должно быть больше 0, Поле не может быть null
    private FormOfEducation formOfEducation; //Поле не может быть null
    private Person groupAdmin; //Поле может быть null

    public StudyGroup(String name, Coordinates coordinates, int studentsCount,
                     long shouldBeExpelled, Float averageMark, FormOfEducation formOfEducation,
                     Person groupAdmin) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (coordinates == null) {
            throw new IllegalArgumentException("Coordinates cannot be null");
        }
        if (studentsCount <= 0) {
            throw new IllegalArgumentException("Students count must be greater than 0");
        }
        if (shouldBeExpelled <= 0) {
            throw new IllegalArgumentException("Should be expelled count must be greater than 0");
        }
        if (averageMark == null || averageMark <= 0) {
            throw new IllegalArgumentException("Average mark must be greater than 0");
        }
        if (formOfEducation == null) {
            throw new IllegalArgumentException("Form of education cannot be null");
        }

        this.id = ID_GENERATOR.getAndIncrement();
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.now();
        this.studentsCount = studentsCount;
        this.shouldBeExpelled = shouldBeExpelled;
        this.averageMark = averageMark;
        this.formOfEducation = formOfEducation;
        this.groupAdmin = groupAdmin;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public long getShouldBeExpelled() {
        return shouldBeExpelled;
    }

    public Float getAverageMark() {
        return averageMark;
    }

    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyGroup that = (StudyGroup) o;
        return id == that.id &&
                studentsCount == that.studentsCount &&
                shouldBeExpelled == that.shouldBeExpelled &&
                Objects.equals(name, that.name) &&
                Objects.equals(coordinates, that.coordinates) &&
                Objects.equals(creationDate, that.creationDate) &&
                Objects.equals(averageMark, that.averageMark) &&
                formOfEducation == that.formOfEducation &&
                Objects.equals(groupAdmin, that.groupAdmin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, studentsCount,
                shouldBeExpelled, averageMark, formOfEducation, groupAdmin);
    }

    @Override
    public String toString() {
        return "StudyGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", studentsCount=" + studentsCount +
                ", shouldBeExpelled=" + shouldBeExpelled +
                ", averageMark=" + averageMark +
                ", formOfEducation=" + formOfEducation +
                ", groupAdmin=" + groupAdmin +
                '}';
    }

    @Override
    public int compareTo(StudyGroup other) {
        return Long.compare(this.id, other.id);
    }
} 