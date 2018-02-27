import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
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
            System.out.println("Readind file:");
            fileLines.forEach(System.out::println);
            System.out.println("Formating data:");

            fileLines = Files.lines(path);
            users = 0;
            String s1 = String.valueOf(fileLines.findAny());
            int k = 0;
            int startPoint = 0;
            int index = 0;
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) == ',') {
                    k++;
                    if (k == 4) {
                        String ab = s1.substring(startPoint, i + 1);
                        startPoint = i + 1;
                        k = 0;
                        users++;
                        index++;
                        System.out.println(index + ". " + ab);

                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nList contains " + users + " users.");

    }


    }

