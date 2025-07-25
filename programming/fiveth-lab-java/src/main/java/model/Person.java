package model;

import java.util.Date;

/**
 * Класс человека.
 */
public class Person {

    private String name;
    private Date birthday;
    private Float height;
    private String passportId;
    private Color eyeColor;

    /**
     * Возвращает имя человека.
     *
     * @return имя человека
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает имя человека.
     *
     * @param name новое имя
     * @throws IllegalArgumentException если имя равно null или пустое
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не должно быть null или пустым");
        }
        this.name = name;
    }

    /**
     * Возвращает дату рождения.
     *
     * @return дата рождения
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Устанавливает дату рождения.
     *
     * @param birthday новая дата рождения
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * Возвращает рост человека.
     *
     * @return рост в сантиметрах
     */
    public Float getHeight() {
        return height;
    }

    /**
     * Устанавливает рост человека.
     *
     * @param height новый рост в сантиметрах
     * @throws IllegalArgumentException если рост меньше или равен нулю или равен null
     */
    public void setHeight(Float height) {
        if (height == null || height <= 0) {
            throw new IllegalArgumentException("Рост должен быть больше нуля и не null");
        }
        this.height = height;
    }

    /**
     * Возвращает номер паспорта.
     *
     * @return номер паспорта
     */
    public String getPassportId() {
        return passportId;
    }

    /**
     * Устанавливает номер паспорта.
     *
     * @param passportId новый номер паспорта
     * @throws IllegalArgumentException если номер паспорта равен null
     */
    public void setPassportId(String passportId) {
        if (passportId == null) {
            throw new IllegalArgumentException("Должен быть не null");
        }
        this.passportId = passportId;
    }

    /**
     * Возвращает цвет глаз.
     *
     * @return цвет глаз
     */
    public Color getEyeColor() {
        return eyeColor;
    }

    /**
     * Устанавливает цвет глаз.
     *
     * @param eyeColor новый цвет глаз
     */
    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    /**
     * Возвращает строковое представление человека.
     *
     * @return строка в формате "Person{name='...', birthday=..., height=... и тд."
     */
    @Override
    public String toString() {
        return String.format("Person{name='%s', birthday=%s, height=%s, "
                        + "passportId='%s', eyeColor=%s}",
                name, birthday, height, passportId, eyeColor);
    }
}
