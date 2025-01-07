package moves;
// Готов
import ru.ifmo.se.pokemon.*;

public class bugBuzz extends SpecialMove {
    public bugBuzz() {
        super(Type.BUG, 90, 100);
    }


    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.1){
            p.setMod(Stat.SPECIAL_DEFENSE, -1);
        }
    }

    @Override
    protected String describe() {
        return "Применил Bug Buzz";
    }
}