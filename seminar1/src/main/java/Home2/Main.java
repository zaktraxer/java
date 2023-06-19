package Home2;

import Home2.actor.Human;
import Home2.market.Market;

public class Main {
    public static void main(String[] args) {


        Market market = new Market();

        market.update();

        Human human1 = new Human("Иван Федоров", 35, "Самара");
        Human human2 = new Human("Полина Иванова", 25, "Углич");
        Human human3 = new Human("Феофан Сидоров", 27, "Ярославль");
        Human human4 = new Human("Терентий Попов", 29, "Вологда");
        Human human5 = new Human("Евлампия Субботина", 32, "Санкт-Петербург");

        market.acceptToMarket(human1);
        market.acceptToMarket(human2);
        market.acceptToMarket(human3);
        market.acceptToMarket(human4);
        market.acceptToMarket(human1);
        market.acceptToMarket(human5);

        market.update();

        human1.setMakeOrder();
        human2.setMakeOrder();
        human3.setMakeOrder();
        human4.setMakeOrder();

        market.update();

        human4.setTakeOrder();
        human1.setTakeOrder();

        market.update();

        market.update();

    }
}