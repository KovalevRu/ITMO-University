package moves;
// ГОТОВ
import ru.ifmo.se.pokemon.*;

public class IronDefense extends StatusMove {
    public IronDefense(){
        super(Type.STEEL,0,0);
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        p.setMod(Stat.DEFENSE, +2);
    }

    @Override
    protected String describe() {
        return "Использовал Iron Defense";
    }
}
