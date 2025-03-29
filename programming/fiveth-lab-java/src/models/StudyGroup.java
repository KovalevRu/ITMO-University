package models;
import utility.Validatable;

public class StudyGroup implements Validatable {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int studentsCount; //Значение поля должно быть больше 0
    private long shouldBeExpelled; //Значение поля должно быть больше 0
    private Float averageMark; //Значение поля должно быть больше 0, Поле не может быть null
    private FormOfEducation formOfEducation; //Поле не может быть null
    private Person groupAdmin; //Поле может быть null

    public StudyGroup(long id,String name,Coordinates coordinates, java.time.LocalDateTime creationDate, int studentsCount, long shouldBeExpelled, Float averageMark, FormOfEducation formOfEducation,Person groupAdmin) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.studentsCount = studentsCount;
        this.shouldBeExpelled = shouldBeExpelled;
        this.averageMark = averageMark;
        this.formOfEducation = formOfEducation;
        this.groupAdmin = groupAdmin;
    }

    @Override
    public boolean isValid() {
        if (id <= 0) return false;
        if (name == null || name.isEmpty()) return false;
        if (coordinates == null) return false;
        if (creationDate == null) return false;
        if (studentsCount <= 0) return false;
        if (shouldBeExpelled <= 0) return false;
        if (averageMark == null || averageMark <= 0) return false;
        if (formOfEducation == null) return false;
        if (groupAdmin == null) return false;
            return true;
    }

}
