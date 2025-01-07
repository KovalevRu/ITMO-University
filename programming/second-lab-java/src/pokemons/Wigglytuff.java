package pokemons;

import moves.Facade;
import ru.ifmo.se.pokemon.*;
// ГОТОВ
public class Wigglytuff extends Jigglypuff{
    public Wigglytuff(String name, int level){
        super(name,level);
        setStats(140,70,45,85,50,45);
        setType(Type.NORMAL,Type.FAIRY);
        setMove(new Facade());

    }
}
