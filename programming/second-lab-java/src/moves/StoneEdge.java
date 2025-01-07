package moves;
// ГОТОВ
import ru.ifmo.se.pokemon.*;

import java.util.Random;

public class StoneEdge extends PhysicalMove {
    public StoneEdge(){
        super(Type.ROCK,100,80);
    }

    @Override
    protected double calcCriticalHit(Pokemon p, Pokemon p1) {

        return (float)1/8 * super.calcCriticalHit(p, p1);
    }

    @Override
    protected String describe() {
        return "Применил Stone Edge";
    }
}
