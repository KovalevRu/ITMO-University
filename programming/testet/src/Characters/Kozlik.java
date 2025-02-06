package Characters;

import java.util.Objects;

public class Kozlik extends Person {
    public Kozlik(String name, int money) {
        super(name, money);
    }

    @Override
    public void act() {
        System.out.println(name + " с любопытством изучает комнату и замечает яркую подсветку.");
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        return o instanceof Kozlik;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), "Kozlik");
    }

    @Override
    public String toString() {
        return "Козлик";
    }
}