import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class FileReader {

    private int users;
    Set<User> usersList = new HashSet<>();

    public void readFile() throws FileReaderException {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("file/names.txt").getFile());

        Path path = Paths.get(file.getPath());

        try {
            Stream<String> fileLines = Files.lines(path);
            System.out.println("Reading file:");
            fileLines.forEach(System.out::println);
            System.out.println("Formating data:");

            fileLines = Files.lines(path);
            String unformatedString = String.valueOf(fileLines.findAny());
            users = 0;
            int counter = 0;
            int startPoint = 0;
            int index = 0;
            for (int i = 0; i < unformatedString.length(); i++) {
                if (unformatedString.charAt(i) == ',') {
                    counter++;
                    if (counter == 4) {
                        String userString = unformatedString.substring(startPoint, i + 1);
                        startPoint = i + 1;
                        counter = 0;
                        users++;
                        index++;
                        System.out.println(index + ". " + userString);

                    }
                }
//                User user = new User();
//                usersList.add(user);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nList contains " + users + " users.");

    }

    public void name() throws FileReaderException {
        InputStream inputStream = null;
        try {
            inputStream = Files.newInputStream(Paths.get("src/main/resources/file/names.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        int c;
        String[] oldest = new String[4];
        String[] temp = new String[4];
        int commaCount = 0;
        StringBuilder sb = new StringBuilder();
        try {
            while ((c = reader.read()) != -1) {
                char character = (char) c;
                if (character == ',') {
                    temp[commaCount] = sb.toString();
                    if (commaCount == 3) { // Full person parsed
                        commaCount = 0;
                        oldest = substituteIfOlder(temp, oldest);
                        temp = new String[4];
                    } else {
                        commaCount++; // Part of person parsed
                    }
                    sb = new StringBuilder();
                } else {
                    sb.append(character);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("The oldest user in the list: \n" + Arrays.toString(oldest));

    }

    private String[] substituteIfOlder(String[] temp, String[] oldest) {
        if (oldest[2] == null) {
            return temp;
        }
        return LocalDate.parse(oldest[2]).isBefore(LocalDate.parse(temp[2])) ? oldest : temp;
    }



    public void findOldestUser(Set<User> usersList) {}




    }

