import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class FileReader {
    private String firstName = null;
    private String lastName = null;
    private LocalDate birthDate = null;
    private int phone= 0;
    private int counter = 0;
    private int usersQuantity = 0;

    Set<User> usersList = new HashSet<>();

    public void readFile() throws FileReaderException {


        try {
            Stream<String> unformatedFile = Files.lines(Paths.get("src/main/resources/file/names.txt"));
            System.out.println("Reading file:");
            unformatedFile.forEach(System.out::println);
            System.out.println("Formating data:");

            unformatedFile = Files.lines(Paths.get("src/main/resources/file/names.txt"));
            String splited = String.valueOf(unformatedFile.findAny());
            String[] splitedArray;
            splitedArray = splited.split(",");
            for (int i = 0; i < splitedArray.length; i++) {
                counter++;
                if (counter == 1) {
                    firstName = splitedArray[i];
                } else if (counter == 2) {
                    lastName = splitedArray[i];
                } else if (counter == 3) {
                    birthDate = LocalDate.parse(splitedArray[i]);
                } else if (counter == 4) {
                    phone = Integer.parseInt(splitedArray[i]);
                    counter = 0;
                    usersQuantity++;
                    User user = new User(firstName,lastName, birthDate, phone);
                    usersList.add(user);
                    System.out.println(usersQuantity + ". " + user.getFirstname() + " " + user.getLastName() + " " + user.getBirthDate()
                            + " " + user.getPhone());

                }
            }


        } catch (IOException e) {
            throw new FileReaderException();
        }

        System.out.println("\nList contains " + usersQuantity + " users.");

    }


    public void findOldestUser(Set<User> usersList) {}




    }

