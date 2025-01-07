package moves;
// ГОТОВ
import ru.ifmo.se.pokemon.*;

public class PoisonJab extends PhysicalMove {
    public PoisonJab(){
        super(Type.POISON,80,100);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.3){
            Effect.poison(p);
        }
    }

    @Override
    protected String describe() {
        return "Применил Poison Jab";
    }
}
