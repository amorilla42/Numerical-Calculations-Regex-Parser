import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        try(Scanner sc = new Scanner(new File("test.txt"))){

        }catch (NoSuchElementException e){
            e.printStackTrace();
        }




    }
}