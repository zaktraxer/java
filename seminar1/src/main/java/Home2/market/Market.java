package Home2.market;

import Home2.actor.Actor;
import Home2.actor.Human;

import java.util.ArrayList;
import java.util.List;

public class Market implements QueueBehaviour, MarketBehaviour {

    private final List<Actor> actors;

    public Market() {
        actors = new ArrayList<>();
    }

    @Override
    public void acceptToMarket(Actor actor) {
        boolean isNameFound = false;
        for (Actor actorInList : actors) {
            if ((actorInList instanceof Human)
                    && ((Human) actorInList).getName().equals(((Human) actor).getName())) {
                System.out.printf("Пользователь: %s уже имеет доступ на маркетплейс%n",
                        (((Human) actor).getName()));
                isNameFound = true;
                break;
            }
        }
        if (!isNameFound && actor instanceof Human) {
            actors.add(actor);
            System.out.printf("Пользователь: %s -> Получил доступ на маркетплейс%n",
                    (((Human) actor).getName()));
        }
    }

    @Override
    public void releaseFromMarket(List<Actor> actors) {
        for (Actor actorInList : actors) {
            if (actorInList.isTakeOrder()) {
                System.out.printf("Пользователь: %s -> получил заказ и был выгружен из маркетплейса",
                        (((Human) actorInList).getName()));
            }
        }
    }

    @Override
    public void update() {
        int lineWidth = 60, counter = 1;
        String lineChar = "-";
        System.out.println();
        System.out.println(lineChar.repeat(lineWidth));
        System.out.println("Состояние маркетплейса: ");
        for (Actor actor : actors) {
            if (actor instanceof Human) {
                takeInQueue(actor);
            }
        }
        takeOrders();
        giveOrders();
        System.out.println(lineChar.repeat(lineWidth));
        System.out.printf("| %-2s| %-18s | %-14s | %-14s |%n", "№", "Имя пользователя", "Заказ размещен", "Заказ получен");
        System.out.println(lineChar.repeat(lineWidth));
        for (Actor actor : actors) {
            System.out.printf("| %-2s| %-18s |", counter, ((Human) actor).getName());
            if (actor.isMakeOrder()) {
                System.out.printf(" %7s%8s|", "+", " ");
            } else {
                System.out.printf(" %15s|", " ");
            }
            if (actor.isTakeOrder()) {
                System.out.printf(" %7s%8s|", "+", " ");
            } else {
                System.out.printf(" %15s|", " ");
            }
            System.out.printf("%n");
            counter++;
        }
        System.out.println(lineChar.repeat(lineWidth));
        releaseFromQueue();
    }

    @Override
    public void takeInQueue(Actor actor) {
        boolean isUserFound = false;
        for (Actor actorInList : actors) {
            if ((actorInList instanceof Human)
                    && ((Human) actorInList).getName().equals(((Human) actor).getName())
                    && actor.isMakeOrder()) {
                System.out.printf("Пользователь: %s -> Заказ в очереди обработки%n",
                        (((Human) actor).getName()));
                isUserFound = true;
                break;
            }
            if ((actorInList instanceof Human)
                    && ((Human) actorInList).getName().equals(((Human) actor).getName())) {
                System.out.printf("Пользователь: %s -> Заказа ещё нет -> не в очереди%n",
                        (((Human) actor).getName()));
                isUserFound = true;
                break;
            }
        }
        if (!isUserFound && actor instanceof Human) {
            System.out.printf("Пользователь: %s не найден на маркетплейсе%n",
                    (((Human) actor).getName()));
        }
    }

    @Override
    public void takeOrders() {
        boolean isUserFound = false;
        for (Actor actor : actors) {
            if ((actor instanceof Human) && actor.isMakeOrder()) {
                System.out.printf("Пользователь: %s -> Заказ был принят маркетплейсом%n",
                        (((Human) actor).getName()));
                isUserFound = true;
            }
        }
        if (!isUserFound) {
            System.out.println("Принятие заказов -> Необработанных заказов нет");
        }
    }

    @Override
    public void giveOrders() {
        boolean isUserFound = false;
        for (Actor actor : actors) {
            if ((actor instanceof Human)
                    && actor.isMakeOrder()
                    && !actor.isTakeOrder()) {
                System.out.printf("Пользователь: %s -> Заказ готов к выдаче%n",
                        (((Human) actor).getName()));
                isUserFound = true;
            }
        }
        if (!isUserFound) {
            System.out.println("Выдача заказов -> Необработанных заказов нет");
        }
    }

    @Override
    public void releaseFromQueue() {
        for (int i = actors.size() - 1; i >= 0; i--) {
            if ((actors.get(i) instanceof Human) && actors.get(i).isTakeOrder()) {
                System.out.printf("Пользователь: %s -> Заказ был получен покупателем%n",
                        (((Human) actors.get(i)).getName()));
                System.out.printf("Пользователь: %s -> Удален из очереди маркетплейса%n",
                        (((Human) actors.get(i)).getName()));
                actors.remove(actors.get(i));
            }
        }
    }
}