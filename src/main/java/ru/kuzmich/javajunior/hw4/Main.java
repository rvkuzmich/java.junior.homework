package ru.kuzmich.javajunior.hw4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.kuzmich.javajunior.hw4.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/*
Задание: Настройте связь между вашим приложением и базой данных MySQL с использованием Hibernate.
Создайте несколько объектов Person и сохраните их в базу данных.
 */
public class Main {
    public static void main(String[] args) {
//        String url = "jdbc:mysql://localhost:3306/";
//        String user = "root";
//        String password = "krv9603822416";
        try(SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();
            Person person1 = new Person("Roman", 38);
            session.save(person1);
            Person person2 = new Person("Alexey", 33);
            session.save(person2);
            System.out.println("Person saved successfully");

            Person rPerson = session.get(Person.class, person2.getId());
            System.out.println("Person retrieved successfully");
            System.out.println(rPerson);

            rPerson.setName("Daria");
            rPerson.setAge(39);
            session.update(rPerson);
            System.out.println("Person updated successfully");

            session.delete(rPerson);
            System.out.println("Person deleted successfully");

//            Person person2 = new Person("Alexey", 33);
//            Person person3 = new Person("Daria", 39);
//            Person person4 = new Person("Vladimir", 72);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


//        try (Connection connection = DriverManager.getConnection(url, user, password)) {
//
//
//            createDatabase(connection);
//            System.out.println("Database created successfully");
//
//            useDatabase(connection);
//            System.out.println("Database used successfully");
//
//            dropTable(connection);
//            System.out.println("Table dropped successfully");
//
//            createTable(connection);
//            System.out.println("Table created successfully");
//
//            insertData(connection, person1);
//            insertData(connection, person2);
//            insertData(connection, person3);
//            insertData(connection, person4);
//            System.out.println("Data inserted successfully");
//
//            List<Person> people = readData(connection);
//            for (var p : people) {
//                System.out.println(p);
//            }
//            System.out.println("Data read successfully");
//
//            people.get(3).setName("Ekaterina");
//            people.get(3).setAge(41);
//            updateData(connection, people.get(3));
//            System.out.println("Data updated successfully");
//
//            people = readData(connection);
//            for (var p : people) {
//                System.out.println(p);
//            }
//            System.out.println("Data read successfully");
//
//            deleteData(connection, 2);
//            System.out.println("Data deleted successfully");
//
//            people = readData(connection);
//            for (var p : people) {
//                System.out.println(p);
//            }
//            System.out.println("Data read successfully");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


    }

//   private static void createDatabase(Connection connection) {
//        String createDatabaseSql = "CREATE DATABASE IF NOT EXISTS personsDB;";
//        try (PreparedStatement statement = connection.prepareStatement(createDatabaseSql)) {
//            statement.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void useDatabase(Connection connection) {
//        String useDatabaseSql = "USE personsDB;";
//        try (PreparedStatement statement = connection.prepareStatement(useDatabaseSql)) {
//            statement.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void createTable(Connection connection) {
//        String createTable = "CREATE TABLE IF NOT EXISTS persons(id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50)," +
//                "age INT);";
//        try (PreparedStatement statement = connection.prepareStatement(createTable)) {
//            statement.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void dropTable(Connection connection) {
//        String dropTable = "DROP TABLE IF EXISTS persons;";
//        try (PreparedStatement statement = connection.prepareStatement(dropTable)) {
//            statement.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void insertData(Connection connection, Person person) {
//        String insertDataSql = "INSERT INTO persons (name, age) VALUES (?, ?);";
//        try (PreparedStatement statement = connection.prepareStatement(insertDataSql)) {
//            statement.setString(1, person.getName());
//            statement.setInt(2, person.getAge());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static List<Person> readData(Connection connection) {
//        ArrayList<Person> people = new ArrayList<>();
//        String readDataSql = "SELECT * FROM persons;";
//        try (PreparedStatement statement = connection.prepareStatement(readDataSql)) {
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                int age = resultSet.getInt("age");
//                people.add(new Person(id, name, age));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return people;
//    }
//    private static void updateData(Connection connection, Person person) {
//        String updateDataSql = "UPDATE persons SET name=?, age=? WHERE id=?;";
//        try (PreparedStatement statement = connection.prepareStatement(updateDataSql)) {
//            statement.setString(1, person.getName());
//            statement.setInt(2, person.getAge());
//            statement.setInt(3, person.getId());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    private static void deleteData(Connection connection, int id) {
//        String deleteDataSql = "DELETE FROM persons WHERE id=?;";
//        try (PreparedStatement statement = connection.prepareStatement(deleteDataSql)) {
//            statement.setLong(1, id);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }/
}
