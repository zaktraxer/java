import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        // Создать множество, ключ и значение строки. Добавить шесть элементов. Вывести в консоль значения.
        HashMap<Integer, String> heroes = new HashMap<>();
        heroes.put(4, "Фродо");
        heroes.put(8, "Сем");
        heroes.put(16, "Леголас");
        heroes.put(32, "Гэндальф");
        heroes.put(64, "Арагорн");
        heroes.put(128, "Галадриэль");
        heroes.forEach((k, v) -> System.out.println(k + " " + v));
        System.out.println(heroes);

        // Добавить ко всем значениям символ "!".
        heroes.computeIfPresent(4, (k, v) -> v + '!');
        heroes.computeIfPresent(8, (k, v) -> v + '!');
        heroes.computeIfPresent(16, (k, v) -> v + '!');
        heroes.computeIfPresent(32, (k, v) -> v + '!');
        heroes.computeIfPresent(64, (k, v) -> v + '!');
        heroes.computeIfPresent(128, (k, v) -> v + '!');
        System.out.println(heroes);
        heroes.forEach((k, v) -> System.out.println(k + " " + v));
        System.out.println(heroes);

        // Создать второе множество с таким же обобщением. Ключи второго множества частично совпадают с ключами первого.
        HashMap<Integer, String> heroesSec = new HashMap<>();
        heroesSec.put(2, "Бильбо");
        heroesSec.put(1, "Теоден");
        heroesSec.put(16, "Фарамир");
        heroesSec.put(8, "Гимли");
        heroesSec.put(4, "Саурон");
        heroesSec.put(6, "Голлум");
        System.out.println(heroesSec);

        // Объединить значения во втором множестве и первом, если ключи совпадают. Вывести результат в консоль.
        for (Integer item : heroes.keySet()) {
            heroes.merge(item, heroesSec.getOrDefault(item, ""), (newVal, tempVal) -> newVal + tempVal);
        }
        System.out.println(heroes);
        heroes.forEach((k, v) -> System.out.println(k + " " + v));
    }
}