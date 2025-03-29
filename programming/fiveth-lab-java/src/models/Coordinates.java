package models;

import utility.Validatable;

public class Coordinates implements Validatable {
    private Double x; //Поле не может быть null
    private double y;

    @Override
    public boolean isValid() {
        if (x == null) return false;
            return true;
    }
}
