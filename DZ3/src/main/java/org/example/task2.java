//Задача 2. Пусть дан произвольный список целых чисел, удалить из него чётные числа

import java.util.*;

public class task2 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 3, 8, 1, 2, 9, 7));
        System.out.println("До удаления четных чисел: " + list);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            int number = iterator.next();
            if (number % 2 == 0) {
                iterator.remove();
            }
        }
        System.out.println("После удаления четных чисел: " + list);
    }
}