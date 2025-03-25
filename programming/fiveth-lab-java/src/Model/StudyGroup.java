package Model;

public class StudyGroup {
    private long id; // //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int studentsCount;
    private long shouldBeExpelled;
    private Float averageMark;
    private FormOfEducation formOfEducation;
    private Person groupAdmin;


    public StudyGroup(String name, Coordinates coordinates, java.time.LocalDate creationDate,) {

    }
}
