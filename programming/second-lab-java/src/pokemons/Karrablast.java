package pokemons;

import moves.PoisonJab;
import moves.X_Scissor;
import moves.bugBuzz;
import ru.ifmo.se.pokemon.*;
// ГОТОВ
public class Karrablast extends Pokemon {
    public Karrablast(String name, int level){
        super(name,level);
        setStats(50,75,45,40,45,60);
        setType(Type.BUG);
        setMove(new bugBuzz(), new PoisonJab(), new X_Scissor());
    }
}
