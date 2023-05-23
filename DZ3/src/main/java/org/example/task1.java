//Задача 1. Реализовать алгоритм обратной сортировки списков компаратором.
import java.util.*;

public class task1 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 3, 8, 1, 2, 9, 7));
        System.out.println("Before sorting: " + list);
        Comparator<Integer> comparator = Collections.reverseOrder();
        Collections.sort(list, comparator);
        System.out.println("After sorting: " + list);
    }
}