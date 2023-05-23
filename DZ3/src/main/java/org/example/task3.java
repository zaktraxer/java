//Задача 3. Задан целочисленный список ArrayList. Найти минимальное, максимальное и среднее из этого списка.

import java.util.*;

public class task3 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 3, 8, 1, 2, 9, 7));
        System.out.println("List: " + list);
        int min = Collections.min(list);
        int max = Collections.max(list);
        double average = list.stream().mapToDouble(i -> i).average().orElse(0.0);
        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
        System.out.println("Average: " + average);
    }
}