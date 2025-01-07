package moves;
// ГОТОВ
import ru.ifmo.se.pokemon.*;

public class Wake_UpSlap extends PhysicalMove {
    public Wake_UpSlap(){
        super(Type.FIGHTING,70,100);
    }

    @Override
    protected void applyOppDamage(Pokemon p, double damage) {
        Status defStatus = p.getCondition();
        if (defStatus.equals(Status.SLEEP)) {
            p.setMod(Stat.HP, 2 * (int) damage);
        } else {
            p.setMod(Stat.HP, (int) damage);
        }
    }

    @Override
    protected String describe() {
        return "Применил Wake-Up Slap";
    }
}
