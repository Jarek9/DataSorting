public class Application {

    public static void main(String args[]) {
        FileReader fileReader = new FileReader();
        try {
            fileReader.readFile();
        } catch (FileReaderException e) {
            System.out.println("Problem with reading file!!!");
        }
    }
}
