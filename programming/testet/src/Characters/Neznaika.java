package Characters;

import java.util.Objects;

public class Neznaika extends Person {
    public Neznaika(String name, int money) {
        super(name, money);
    }

    @Override
    public void act() {
        System.out.println(name + " осматривается по сторонам и удивляется обстановке номера.");
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        return o instanceof Neznaika;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), "Neznaika");
    }

    @Override
    public String toString() {
        return "Незнайка";
    }
}