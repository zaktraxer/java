//Задача 5. Создать ArrayList<Integer> и добавить нулевым эллементом ноль 10000 раз.
//          Повторить пятый пункт но с LinkedList.
//          Сравнить время работы пятого и шестого пунктов.
import java.util.ArrayList;
import java.util.LinkedList;

public class task5 {
    public static void main(String[] args) {
        long startTime, endTime;
        float duration;

        // ArrayList
        startTime = System.nanoTime();
        ArrayList<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list1.add(0);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1e9f;
        System.out.println("ArrayList time: " + duration);

        // LinkedList
        startTime = System.nanoTime();
        LinkedList<Integer> list2 = new LinkedList<>();
        for (int i = 0; i < 10000; i++) {
            list2.add(0);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1e9f;
        System.out.println("LinkedList time: " + duration);
    }
}