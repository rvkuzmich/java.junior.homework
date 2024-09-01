package ru.kuzmich.javajunior.hw1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/*Напишите программу, которая использует Stream API для обработки списка чисел. Программа должна вывести
на экран среднее значение всех четных чисел в списке.
 */
public class Main {
    public static void main(String[] args) {
        List<Integer> integers = fillArray();
        System.out.printf("Начальный список - %s", integers);
        System.out.println();
        integers = integers.stream().filter(num -> num % 2 == 0 && num > 0).toList();
        if (integers.isEmpty()) {
            System.out.println("Четных чисел в списке нет");
        } else {
            AtomicInteger sum = new AtomicInteger();
            System.out.printf("Отфильтрованный список - %s", integers);
            integers.forEach(sum::addAndGet);
            System.out.println();
            double n = sum.doubleValue();
            System.out.printf("Среднее значение четных чисел в списке - %.3f", n / integers.size());
        }
    }
    public static ArrayList<Integer> fillArray() {
        Random random = new Random();
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            integers.add(random.nextInt(10));
        }
        return integers;
    }
}
