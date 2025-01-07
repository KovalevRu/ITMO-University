package pokemons;
import ru.ifmo.se.pokemon.*;
import moves.*;
// ГОТОВ
public class Entei extends Pokemon {
    public Entei(String name, int level){
        super(name,level);
        setStats(115,115,85,90,75,100);
        setType(Type.FIRE);
        setMove(new Leer(), new FireFang(), new Swagger(), new StoneEdge());
    }
}
