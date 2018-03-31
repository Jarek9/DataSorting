package com.testing.core;

import exceptionService.FileFormatterException;
import org.junit.*;
import user.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FileFormatterTestSuite {

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private int phone;
    private int counter;
    private int usersQuantity;
    private LocalDate currentDate = LocalDate.now();
    private String oldestUser;
    private long age;
    private static final int FIRST_ELEMENT = 1;
    private static final int SECOND_ELEMENT = 2;
    private static final int THIRD_ELEMENT = 3;
    private static final int FOURTH_ELEMENT = 4;
    private static final int START = 0;
    private String splited;
    private static final String testString = "Abundancja,Drobicz,1970-03-09,600490000,Abundancjusz,Dabi,1972-12-01,600490001," +
            "Achacja,Dapper,1980-04-15,600490002,Achacjusz,Daugielewicz,1995-06-08,600490003," +
            "Achacy,Dejwór,1998-01-04,600490004,Achilles,Deptuka,2000-12-30,600490005," +
            "Ada,Deriglasoff,2002-09-30,600490006";

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Starting the tests");
    }
    @AfterClass
    public static void afterClass() {
        System.out.println("All tests finished ! ");
    }

    @Test
    public void testReadFile() throws FileFormatterException {
        //Given
        final String FILE_PATH = "src/test/resources/testFile/testNames.txt";
        //When
        try (InputStream inputStream = Files.newInputStream(Paths.get(FILE_PATH))){

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            splited = reader.readLine();
            System.out.println("Testing reading data from file: \n" + splited);
        } catch (IOException e) {
            throw new FileFormatterException();
        }
        //Then
        Assert.assertEquals(testString,splited);
    }

    @Test
    public void testFormatFile() throws FileFormatterException {
        //Given
        final String FILE_PATH = "src/test/resources/testFile/testNames.txt";
        Set<User> usersList = new HashSet<>();
        //When
        try (InputStream inputStream = Files.newInputStream(Paths.get(FILE_PATH))){

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            splited = reader.readLine();
        } catch (IOException e) {
            throw new FileFormatterException();
        }
        String[] splitedArray;
        splitedArray = splited.split(",");
        for (int i = 0; i < splitedArray.length; i++) {
            counter++;
            if (counter == FIRST_ELEMENT) {
                firstName = splitedArray[i].substring(splitedArray[i].indexOf("[") + FIRST_ELEMENT);
            } else if (counter == SECOND_ELEMENT) {
                lastName = splitedArray[i];
            } else if (counter == THIRD_ELEMENT) {
                birthDate = LocalDate.parse(splitedArray[i]);
            } else if (counter == FOURTH_ELEMENT) {
                phone = Integer.parseInt(splitedArray[i]);
                counter = START;
                usersQuantity++;
                User user = new User(firstName, lastName, birthDate, phone);
                usersList.add(user);
            }
        }
        System.out.println("Testing formating data: \n" + usersList);
        //Then
        Assert.assertEquals(7, usersQuantity);
    }

    @Test
    public void testFindOldestUser() {
        //Given
        Set<User> usersList = new HashSet<>();
        usersList.add(new User("Abundancja", "Drobicz", LocalDate.parse("1970-03-09"), 600490000));
        usersList.add(new User("Abundancjusz", "Dabi", LocalDate.parse("1972-12-01"), 600490001));
        usersList.add(new User("Achacja", "Dapper", LocalDate.parse("1980-04-15"), 600490002));
        usersList.add(new User("Achacjusz", "Daugielewicz", LocalDate.parse("1995-06-08"), 600490003));
        usersList.add(new User("Achacy", "Dejwór", LocalDate.parse("1998-01-04"), 600490004));
        usersList.add(new User("Achilles", "Deptuka", LocalDate.parse("1960-12-30"), 600490005));
        usersList.add(new User("Ada", "Deriglasoff", LocalDate.parse("2002-09-30"), 600490006));
        //When
        List<User> sortedList = usersList.stream().sorted(Comparator.comparing(User::getBirthDate)).collect(Collectors.toList());
        age = currentDate.getYear() - sortedList.get(START).getBirthDate().getYear();
        oldestUser = (sortedList.get(START).getFirstname() + " " + sortedList.get(0).getLastName() + " " + age
                + " years old" + " " + sortedList.get(START).getPhone());
        System.out.println("Testing sorting data by birth date: \n " + sortedList);
        //Then
        Assert.assertEquals("Achilles", sortedList.get(START).getFirstname());
        Assert.assertEquals("Deptuka", sortedList.get(START).getLastName());
        Assert.assertEquals(LocalDate.parse("1960-12-30"), sortedList.get(START).getBirthDate());
        Assert.assertEquals(58, age);
        Assert.assertEquals("Daugielewicz", sortedList.get(FOURTH_ELEMENT).getLastName());
        Assert.assertEquals(600490001, sortedList.get(SECOND_ELEMENT).getPhone());

    }

}