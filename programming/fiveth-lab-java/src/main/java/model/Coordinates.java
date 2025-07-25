package model;

/**
 * Класс с координатами .
 */
public class Coordinates {

    private Double x1;
    private double y1;

    /**
     * Возвращает координату X.
     *
     * @return значение координаты X
     */
    public Double getX1() {
        return x1;
    }

    /**
     * Устанавливает координату X.
     *
     * @param x1 новое значение координаты X
     * @throws IllegalArgumentException если значение равно null
     */
    public void setX1(Double x1) {
        if (x1 == null) {
            throw new IllegalArgumentException("Координата X не может быть null");
        }
        this.x1 = x1;
    }

    /**
     * Возвращает координату Y.
     *
     * @return значение координаты Y
     */
    public double getY1() {
        return y1;
    }

    /**
     * Устанавливает координату Y.
     *
     * @param y1 новое значение координаты Y
     */
    public void setY1(double y1) {
        this.y1 = y1;
    }

    /**
     * Возвращает строковое представление координат.
     *
     * @return строка в формате "Координаты{x=X, y=Y}"
     */
    @Override
    public String toString() {
        return "Координаты{"
                + "x=" + x1
                + ", y=" + y1
                + '}';
    }
}
