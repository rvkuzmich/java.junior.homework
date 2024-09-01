package ru.kuzmich.javajunior.hw3.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ru.kuzmich.javajunior.hw3.task2.PersonsListApp.*;

/*
Задание 2: Используя JPA, создайте базу данных для хранения объектов класса Person. Реализуйте методы для добавления,
обновления и удаления объектов Person.
 */
public class Main {
    public static void main(String[] args) {
        List<Person> persons;
        File file = new File(FILE_JSON);
        if (file.exists() && !file.isDirectory()) {
            persons = readPersonsFromFile(FILE_JSON);
        } else {
            persons = preparePersonList();
        }
        savePersonsToFile(FILE_JSON, persons);
        savePersonsToFile(FILE_XML, persons);
        savePersonsToFile(FILE_BIN, persons);
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("Choose option:\n1.Show person list\n2.Add new person\n3.Renew person\n4.Delete person\n5.Exit");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    displayPersons(persons);
                    break;
                case "2":
                    addPerson(sc, persons);
                    displayPersons(persons);
                    break;
                case "3":
                    renewPerson(sc, persons);
                    displayPersons(persons);
                    break;
                case "4":
                    removePersonFromList(sc, persons);
                    displayPersons(persons);
                    break;
                case "5":
                    savePersonsToFile(FILE_JSON, persons);
                    savePersonsToFile(FILE_XML, persons);
                    savePersonsToFile(FILE_BIN, persons);
                    System.out.println("Good bye");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("There is no such option");
                    break;
            }
        }

    }
    static List<Person> preparePersonList() {
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("Roman", 38));
        list.add(new Person("Alexey", 33));
        list.add(new Person("Daria", 39));
        return list;
    }
}
