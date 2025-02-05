import Objects.*;
import Action.*;
public class Main {
    public static void main(String[] args) {
        Person Kozlik = new Person("Козлик",40);
        Person Neznaika = new Person("Незнайка", 90);
        System.out.println(Kozlik.getName() + " и " + Neznaika.getName() +  " идут быстрым шагом, пытаясь уйти от подозрительного мужчины в шляпе и плаще.");
        // Подбросят монетку, кто платит за номер.
        //  Если у проигравшего недостаточно денег, то он обещает победившему вернуть долг
        // Если у победившего нету денег, то они идут на улицу искать отель подешевле
        // OrelAndRezhka game = new OrelAndRezhka(Kozlik.getName(),Neznaika.getName());
        Hotel hotel = new Hotel ("Копеечка",40,80,400);
        Signboard viveska = new Signboard("Самые дешевые номера на свете");
        System.out.println("Вдруг " + Kozlik.getName() + " и " + Neznaika.getName() + " заметили мигающую вывеску: " + viveska.getTextOnSingBoard() + ".");
        System.out.println("Подойдя ближе они увидели отель под названием: " + "\"" + hotel.getHotelName() + "\"" + " и прайс.");
        System.out.println("Самый дешевый номер: " + hotel.getLowestRoomPrice() + " сантиков, " +
                "номер среднего класса: " + hotel.getMediumRoomPrice() + " сантиков, " +
                "и самый дорогой номер: " + hotel.getHighestRoomPrice() + " сантиков.");
        System.out.println(Kozlik.getName() + " вскрикнул: \"Давай переждем в отеле.\"");
        System.out.println(Neznaika.getName() + " ответил: \"Это ужасная идея, что ему мешает пойти за нами?\"");
        System.out.println(Kozlik.getName() + " ответил: \"Давай бросим монетку, если орел, то идем в отель, если решка - бежим домой.\"");
        System.out.println(Neznaika.getName() + " крикнул: \"Бросай быстрее!\"");
        MoneyChecker moneyChecker = new MoneyChecker();
        boolean enoughMoney = moneyChecker.hasEnoughMoney(Kozlik,Neznaika,40);
        OrelAndRezhka orelAndRezhka = new OrelAndRezhka();
        if (orelAndRezhka.OrelOrRezhka() && enoughMoney == false) {
            System.out.println("Выпал орел");
            System.out.println(Kozlik.getName() + " Сказал: \"Побежали в отель!!!\"");
            System.out.println("Забежав в отель " + Kozlik.getName() + " и " + Neznaika.getName() + " поняли, что у них не хватает денег");
            System.out.println("Они развернулись к выходу, но уже было поздно...");
        }else if (orelAndRezhka.OrelOrRezhka() == false && enoughMoney == true) {
            System.out.println("Выпала решка");
            System.out.println(Kozlik.getName() + " и " + Neznaika.getName() + " побежали домой, но не добижали...");
        }else if (orelAndRezhka.OrelOrRezhka() == true && enoughMoney == true) {
            System.out.println("Выпал орел");
            System.out.println(Kozlik.getName() + " и " + Neznaika.getName() + " забежали в отель, оплатили номер, взяли ключ и побежали в номер.");
            System.out.println("Они забежали в номер");
            Room room207 = new Room(101);
        }else{
            System.out.println("Выпала решка");
            System.out.println(Kozlik.getName() + " и " + Neznaika.getName() + " побежали домой, но не добижали...");
        }

    }
}