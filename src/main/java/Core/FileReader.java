package Core;

import ExceptionService.FileReaderException;
import User.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FileReader {
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

    Set<User> usersList = new HashSet<>();

    public void readFile() throws FileReaderException {
        try {
            InputStream inputStream = Files.newInputStream(Paths.get("src/main/resources/file/names.txt"));
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            System.out.println("Reading file:" + reader.readLine());
        } catch (IOException e) {
            throw new FileReaderException();
        }
    }

    public void formatFile() throws FileReaderException {
        System.out.println("Formating data:");
        try {
            InputStream inputStream = Files.newInputStream(Paths.get("src/main/resources/file/names.txt"));
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            splited = reader.readLine();
        } catch (IOException e) {
            throw new FileReaderException();
        }
        String[] splitedArray;
        splitedArray = splited.split(",");
        for (int i = 0; i < splitedArray.length; i++) {
            counter++;
            if (counter == 1) {
                firstName = splitedArray[i].substring(splitedArray[i].indexOf("[") + 1);
            } else if (counter == 2) {
                lastName = splitedArray[i];
            } else if (counter == 3) {
                birthDate = LocalDate.parse(splitedArray[i]);
            } else if (counter == 4) {
                phone = Integer.parseInt(splitedArray[i]);
                counter = 0;
                usersQuantity++;
                User user = new User(firstName, lastName, birthDate, phone);
                usersList.add(user);
                System.out.println(usersQuantity + ". " + user.getFirstname() + " " + user.getLastName()
                        + " " + user.getBirthDate()
                        + " " + user.getPhone());
            }
        }
        System.out.println("\nList contains " + usersQuantity + " users.");
    }

    public void findOldestUser() {
        List<User> sortedList = usersList.stream().sorted(Comparator.comparing(User::getBirthDate)).collect(Collectors.toList());
        age = (currentDate.toEpochDay() - sortedList.get(0).getBirthDate().toEpochDay())/ 365;
        oldestUser = (sortedList.get(0).getFirstname() + " " + sortedList.get(0).getLastName() + " " + age
                + " years old" + " " + sortedList.get(0).getPhone());
        System.out.println("\nThe oldest user on list is " + oldestUser);
    }
}

