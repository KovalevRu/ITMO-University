package Action;
import Objects.Person.*;
import java.util.Random;

public class OrelAndRezhka {
    public boolean OrelOrRezhka(){
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        if (randomNumber < 50) {
            return true; // Идут в отель
        }else{
            return false; // Бегут домой
        }
    }

}
