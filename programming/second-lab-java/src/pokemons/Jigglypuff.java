package pokemons;

import moves.Wake_UpSlap;
import ru.ifmo.se.pokemon.*;
// ГОТОВ
public class Jigglypuff extends Igglybuff{
    public Jigglypuff(String name, int level){
        super(name,level);
        setStats(115,45,20,45,25,20);
        setType(Type.NORMAL,Type.FAIRY);
        setMove(new Wake_UpSlap());
    }
}
