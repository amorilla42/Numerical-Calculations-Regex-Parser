import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Lexer lexer = new Lexer();

        try(Scanner sc = new Scanner(new File("test.txt"))){
            List<String> tokens;
            while(sc.hasNextLine())
            {
                tokens = lexer.tokenizer(sc.nextLine());
                System.out.println(tokens);
            }



        }catch (NoSuchElementException e){
            e.printStackTrace();
        }




    }
}