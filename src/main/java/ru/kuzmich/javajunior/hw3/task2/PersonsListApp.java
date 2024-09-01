package ru.kuzmich.javajunior.hw3.task2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonsListApp {
    public static final String FILE_JSON = "users.json";
    public static final String FILE_XML = "users.xml";
    public static final String FILE_BIN = "users.bin";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();

    public static void addPerson (Scanner sc, List<Person> persons) {
        System.out.println("Input name:");
        String name = sc.nextLine();
        System.out.println("Input age:");
        int age = sc.nextInt();
        sc.nextLine();
        persons.add(new Person(name, age));
        savePersonsToFile(FILE_JSON, persons);
        savePersonsToFile(FILE_XML, persons);
        savePersonsToFile(FILE_BIN, persons);
        System.out.println("New person added");
    }
    public static void savePersonsToFile(String fileName, List<Person> persons) {
        try {
            if(fileName.endsWith(".json")) {
                objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                objectMapper.writeValue(new File(fileName), persons);
            } else if (fileName.endsWith(".xml")) {
                xmlMapper.writeValue(new File(fileName), persons);
            } else if (fileName.endsWith(".bin")) {
                try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
                    objectOutputStream.writeObject(persons);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void renewPerson(Scanner sc, List<Person> persons) {
        System.out.println("Enter person name to renew:");
        String name = sc.nextLine();
        for (Person p : persons) {
            if (name.equals(p.getName())) {
                System.out.println("Enter new name:");
                p.setName(sc.nextLine());
                System.out.println("Enter new age");
                p.setAge(sc.nextInt());
                sc.nextLine();
            }
        }
    }

    public static List<Person> readPersonsFromFile(String fileName) {
        List<Person> persons = new ArrayList<>();
        File file = new File(fileName);
        if (file.exists()) {
            try {
                if (fileName.endsWith(".json")) {
                    persons = objectMapper.readValue(file, objectMapper.getTypeFactory()
                            .constructCollectionType(List.class, Person.class));
                } else if (fileName.endsWith(".xml")) {
                    persons = xmlMapper.readValue(file, xmlMapper.getTypeFactory()
                            .constructCollectionType(List.class, Person.class));
                } else if (fileName.endsWith(".bin")) {
                    try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                        persons = (List<Person>) objectInputStream.readObject();
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return persons;
    }
    public static void removePersonFromList(Scanner sc, List<Person> persons) {
        System.out.println("Enter person name to delete:");
        String name = sc.nextLine();
        int indx = -1;
        for (int i = 0; i < persons.size(); i++) {
            if (name.equals(persons.get(i).getName())) {
                indx = i;
                break;
            }
        }
        if (indx > -1) {
            persons.remove(indx);
        }
        savePersonsToFile(FILE_JSON, persons);
        savePersonsToFile(FILE_XML, persons);
        savePersonsToFile(FILE_BIN, persons);
        System.out.println("Person deleted successfully");
    }
    public static void displayPersons(List<Person> persons) {
        System.out.println("Person list:");
        int i = 1;
        for (Person p : persons) {
            System.out.println(i + "." + p);
            i++;
        }
    }
}
