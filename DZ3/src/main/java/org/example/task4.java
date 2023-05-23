//Задача 4. Дано два целочисленных списка, объеденить их не допуская элементы второго списка уже встречающиеся в первом.

import java.util.*;

public class task4 {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(5, 3, 8, 1, 2, 9, 7));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 7, 4, 2, 6));
        System.out.println("List 1: " + list1);
        System.out.println("List 2: " + list2);
        list2.removeAll(list1);
        list1.addAll(list2);
        System.out.println("Merged list: " + list1);
    }
}