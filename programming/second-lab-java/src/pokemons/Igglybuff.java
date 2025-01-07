package pokemons;

import moves.Flamethrower;
import moves.Swagger;
import ru.ifmo.se.pokemon.*;
// ГОТОВ
public class Igglybuff extends Pokemon {
    public Igglybuff(String name, int level){
        super(name,level);
        setStats(90,30,15,40,20,15);
        setType(Type.NORMAL,Type.FAIRY);
        setMove(new Swagger(), new Flamethrower());
    }
}
