package ru.kuzmich.javajunior.hw2;

import java.lang.reflect.Method;

/*
Используя Reflection API, напишите программу, которая выводит на экран все методы класса String.
 */
public class Main {
    public static void main(String[] args) {
        try {
            Class<?> str = Class.forName("java.lang.String");
            Method[] methods = str.getDeclaredMethods();
            for(Method method : methods) {
                System.out.println("Метод: " + method);
                }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
