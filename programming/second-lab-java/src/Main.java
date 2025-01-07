import ru.ifmo.se.pokemon.*;
import pokemons.*;
public class Main{
    public static void main(String[] args){
        Battle b = new Battle();
        b.addAlly(new Entei("Землятряс",1));
        b.addAlly(new Karrablast("Жучара",1));
        b.addAlly(new Escavalier("Блатной жучара",1));
        b.addFoe(new Igglybuff("Мелочь пузатая",1));
        b.addFoe(new Jigglypuff("Барбарис",1));
        b.addFoe(new Wigglytuff("Пузач",1));


        b.go();
        
    }
}