package core;

import exceptionService.FileFormatterException;
import user.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FileFormatter {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private int phone;
    private int counter;
    private int usersQuantity;
    private LocalDate currentDate = LocalDate.now();
    private String oldestUser;
    private long age;
    private String splited;
    private static final int FIRST_ELEMENT = 1;
    private static final int SECOND_ELEMENT = 2;
    private static final int THIRD_ELEMENT = 3;
    private static final int FOURTH_ELEMENT = 4;
    private static final int START = 0;
    private static final String FILE_PATH = "src/main/resources/file/names.txt";

    private Set<User> usersList = new HashSet<>();

    public void readFile() throws FileFormatterException {
        try (InputStream inputStream = Files.newInputStream(Paths.get(FILE_PATH))){

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            System.out.println("Reading file:" + reader.readLine());
        } catch (IOException e) {
            throw new FileFormatterException();
        }
    }

    public void formatFile() throws FileFormatterException {
        System.out.println("Formating data:");
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
                createUsersList();
            }
        }
        System.out.println("\nThe list contains " + usersQuantity + " users.");
    }

    public void createUsersList() {
        User user = new User(firstName, lastName, birthDate, phone);
        usersList.add(user);
        System.out.println(usersQuantity + ". " + user.getFirstname() + " " + user.getLastName()
                + " " + user.getBirthDate()
                + " " + user.getPhone());
    }


    public void findOldestUser() {
        List<User> sortedList = usersList.stream().sorted(Comparator.comparing(User::getBirthDate)).collect(Collectors.toList());
        age = currentDate.getYear() - sortedList.get(START).getBirthDate().getYear();
        oldestUser = (sortedList.get(START).getFirstname() + " " + sortedList.get(START).getLastName() + " " + age
                + " years old" + " " + sortedList.get(START).getPhone());
        System.out.println("\nThe oldest user on list is " + oldestUser);
    }

}

