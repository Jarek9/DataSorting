import core.FileFormatter;
import exceptionService.FileFormatterException;

public class Application {

    public static void main(String args[]) {
        FileFormatter fileFormatter = new FileFormatter();
        try {
            fileFormatter.readFile();
            fileFormatter.formatFile();
            fileFormatter.findOldestUser();
        } catch (FileFormatterException e) {
            System.out.println("Can't read the file !");
        }
    }
}