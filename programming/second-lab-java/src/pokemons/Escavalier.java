package pokemons;

import moves.IronDefense;
import moves.PoisonJab;
import moves.X_Scissor;
import moves.bugBuzz;
import ru.ifmo.se.pokemon.*;
// ГОТОВ
public class Escavalier extends Karrablast{
    public Escavalier(String name, int level) {
        super(name, level);
        setStats(70,135,105,60,105,20);
        setType(Type.BUG,Type.STEEL);
        setMove(new IronDefense());
    }
}
