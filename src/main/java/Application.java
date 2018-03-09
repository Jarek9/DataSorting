import Core.FileReader;
import ExceptionService.FileReaderException;

public class Application {

    public static void main(String args[]) {
        FileReader fileReader = new FileReader();
        try {
            fileReader.readFile();
            fileReader.formatFile();
            fileReader.findOldestUser();
        } catch (FileReaderException e) {
            System.out.println("Can't reade the file !");
        }
    }
}